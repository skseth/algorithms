package graphs
import scala.annotation._
import structs._
import scala.collection.mutable._
import scala.util.Sorting._


object Knapsack {
	case class Weight(value:Int, weight:Int)
	private case class SubProblemSolution(optvalue:Int, optweight:Int, weights:List[Weight])
	private val NilSubProblemSolution = SubProblemSolution(0,0,Nil)
}

class Knapsack(maxweight:Int) {
	import Knapsack._
	private var weights = new ArrayBuffer[Weight]()
	private var solution = NilSubProblemSolution
	def optimalWeight = solution.optweight
	def optimalValue = solution.optvalue
	def optimalWeights = solution.weights

	def addWeight(value:Int, weight:Int) = weights += Weight(value,weight)

	@tailrec
	private def solve(problems:List[SubProblem]):SubProblemSolution = {
		problems match {
			case head::Nil if !head.hasProblemsToAdd 	=> head.solve
			case head::tail if !head.hasProblemsToAdd 	=> {
				head.solve
				solve(tail)
			}
			case head::tail if head.hasProblemsToAdd => {
				solve(head.addSubproblems(problems))
			}
			case _ 								=> NilSubProblemSolution
		}
	}

	def optimize() = {
		val problem = subproblem(weights.length-1, maxweight)
		solution = solve(List(problem))
	}

	def printSubproblems = {
		val problem = subproblems((weights.length-1, maxweight))
		problem.print()
	}

	private val subproblems = new HashMap[(Int,Int), SubProblem]()
	private def subproblem(boulderidx:Int, ksweight:Int) = {
		//printf("subproblem %d %d\n", boulderidx, ksweight)
		subproblems.getOrElseUpdate(
									(boulderidx, ksweight),
									SubProblem(boulderidx, ksweight)) 
	}

	private case class SubProblem(boulderidx:Int, ksweight:Int) {
		val boulder = weights(boulderidx)
		var problemsAdded = false

		lazy val excl_subproblem = boulderidx match {
			case idx if idx > 0	=> Some(subproblem(idx-1,ksweight))
			case _				=> None
		}

		lazy val incl_subproblem = (boulderidx, ksweight - boulder.weight) match {
			case (idx, nw) if idx > 0 && nw >= 0 	=> Some(subproblem(idx-1, nw))
			case _ 									=> None
		}

		lazy val solution:SubProblemSolution = (excl_subproblem, incl_subproblem) match {
			case (None,None) => simple_solution
			case (Some(exclproblem), None) => exclproblem.solution
			case (Some(exclproblem), Some(inclproblem)) => 
								bestof_solution(inclproblem.solution, exclproblem.solution)
		}

		def solve = solution

		def hasProblemsToAdd = if (problemsAdded) false else 
			(excl_subproblem, incl_subproblem) match {
				case (None,None) => false
				case _ => true
			}

		def addSubproblems(probs:List[SubProblem]) = {
			problemsAdded = true
			(excl_subproblem, incl_subproblem) match {
				case (None,None) => probs
				case (Some(inclproblem), None) => inclproblem::probs
				case (Some(inclproblem), Some(exclproblem)) => inclproblem::exclproblem::probs
			}
		}

		private def simple_solution = (ksweight - boulder.weight) match {
			case nw if nw > 0 	=> SubProblemSolution(boulder.value,boulder.weight,List(boulder))
			case _ 				=> NilSubProblemSolution
		}

		private def bestof_solution(incl:SubProblemSolution, excl:SubProblemSolution) = 
			if (incl.optvalue + boulder.value <= excl.optvalue) 
				excl 
			else 
				incl_solution(incl)

		private def incl_solution(incl:SubProblemSolution) = 
				SubProblemSolution(incl.optvalue + boulder.value, 
									incl.optweight + boulder.weight,
									boulder::incl.weights)

		def print(indent:Int = 0, maxindent:Int = 2):Unit = {
			if (indent <= maxindent) {
				printf("%s bw - %d kw - %d v - %d w - %d %s\n", " "*indent
										, boulder.weight, ksweight
										, solution.optvalue, solution.optweight, solution.weights)
				val newindent = indent + 2

				(excl_subproblem, incl_subproblem) match {
					case (Some(inclproblem), None) => inclproblem.print(newindent)
					case (Some(inclproblem), Some(exclproblem)) => {
						inclproblem.print(newindent)
						exclproblem.print(newindent)
					}
					case _ =>
				}
			}
		}

	}
}

