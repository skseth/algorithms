package graphs
import scala.annotation._
import structs._
import scala.collection.mutable._
import scala.util.Sorting._


class floydwarshall(v:Int) {
	var lengths = Array.ofDim[Int](v,v)
	var lengths_next = Array.ofDim[Int](v,v)
	var nexts = Array.ofDim[Int](v,v)
	var edgelengths = Array.ofDim[Int](v,v)

	for (i <- 0 to v-1;
		 j <- 0 to v-1) {

		if (i == j) {
			lengths(i)(j) = 0
			nexts(i)(j) = i
		} else {
			lengths(i)(j) = Int.MaxValue
			edgelengths(i)(j) = Int.MaxValue
			nexts(i)(j) = -1
		}
	}

	def addEdge(tail:Int, head:Int, length:Int) = {
		if (length == Int.MaxValue) throw new Exception("length is too large")
		if (lengths(tail-1)(head-1) != Int.MaxValue) throw new Exception("loop or duplicate edge")
		lengths(tail-1)(head-1) = length
		edgelengths(tail-1)(head-1) = length
		nexts(tail-1)(head-1) = head-1
	}

	def add_nooverflow(i:Int, j:Int) = 
		if (i == Int.MaxValue || j == Int.MaxValue) Int.MaxValue else i+j 

	def apsp() = {
		for (k <- 0 to v-1 ) {
			for (i <- 0 to v-1;
				 j <- 0 to v-1) {
				val origlength = lengths(i)(j)
				val viaklength = add_nooverflow(lengths(i)(k),lengths(k)(j))

				if (viaklength < origlength) {
					lengths_next(i)(j) = viaklength
					nexts(i)(j) = nexts(i)(k)
				}
				else {
					lengths_next(i)(j) = origlength
				}
			}

			val temp = lengths

			lengths = lengths_next
			lengths_next = temp
		}
	}

	@annotation.tailrec
	private def paths(i:Int, j:Int,acc:List[(Int,Int,Int)]=Nil, nodes:List[Int] = Nil):List[(Int,Int,Int)] = {
			val next = nexts(i)(j)
			val length = edgelengths(i)(next)
			val edge = (i+1,next+1,length)
			if (nodes.contains(next)) {
				(-1,0,0)::edge::acc
			}
			else if (next == -1) acc
			else if (next == j) edge::acc
			else paths(next,j, edge::acc, next::nodes)
	}

	def shortest_shortestpath = {
		var si = -1
		var sj = -1
		var l = Int.MaxValue

		for (i <- 0 to v-1;
			 j <- 0 to v-1) {
			if (lengths(i)(j) < l) {
				si = i
				sj = j
				l = lengths(i)(j)
			}
		}

		printf("Shortest path - %d %d %d %s\n", si+1, sj+1, l, paths(si,sj, Nil))

		l
	}

	def hasNegativeCycles:Boolean = {
		for (i <- 0 to v-1) {
			if (lengths(i)(i) < 0) {
				printf("Negative cycle - %d %d %s\n", i+1, lengths(i)(i), paths(i,i, Nil))
				return true
			}
		}
		return false
	}

}

object floydwarshall {

	def readFromFile(filename:String) = {
		var lines = scala.io.Source.fromFile(filename).getLines()

		var l = lines.next().split(" ")
		var cNodes = l(0).toInt
		var cEdges = l(1).toInt

		val f = new floydwarshall(cNodes)

		for (i <- 1 to cEdges) {
			val l = lines.next()
			val a = l.split(" ")
			f.addEdge(a(0).toInt, a(1).toInt, a(2).toInt)
		}

		f
	}
}