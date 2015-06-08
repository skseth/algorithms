import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

class TwosatSpec extends FeatureSpec with GivenWhenThen {

  def reportsccs(sccs:List[List[Int]]) = {

  	val rep = Array[Int](0,0,0,0,0)
  	val top5 = sccs take 5 

  	for (j <- 0 to top5.length-1) rep(j) = top5(j).length

  	rep.toList
  }

  def checkMismatchedNodes(sccs:List[List[Int]]) = {
    val map = new HashMap[Int,Int]()
    var mismatched = false
    var sccid = 0
    for (scc <- sccs) {
      sccid += 1
      for (n <- scc) {
        map(n) = sccid
        if (!mismatched && map.contains(-n) && (map(-n) == sccid)) {
          mismatched = true
          println(s"$n and ${-n} are in same scc $sccid")
        }
      }
    }
    mismatched
  }

  def fromEdgeFile(filename:String) = {
    var graph = new Graph[Int]()
    val lines = scala.io.Source.fromFile(filename).getLines()
    val cnodes = lines.next().toInt

    for (i <- 1 to cnodes) {
      graph.addNode(i)
      graph.addNode(-i)
    }

    for (i <- 1 to cnodes) {
      val a = lines.next().split(" ")
      val node1 = a(0).toInt
      val node2 = a(1).toInt

      graph.addEdge(-node1, node2)
      graph.addEdge(-node2, node1)
    }   

    graph
  }

  def runScenario(filename:String, expectedMismatch:Boolean) = {
    given("a file with edge input")
    val g = fromEdgeFile(filename)

    when("when we apply Kosaraju Algorithm")
    val sccs = twosat.kosaraju[Int](g)

    then("we get sccs")
    println(reportsccs(sccs))
    assert(checkMismatchedNodes(sccs) == expectedMismatch)
  }

  feature("twosat") {
    info("I want to find twosat solutions")

    scenario("2-sat 1") {
      runScenario("data/2sat1.txt", false)
    }

    scenario("2-sat 2") {
    	runScenario("data/2sat2.txt", true)
	  }

    scenario("2-sat 3") {
      runScenario("data/2sat3.txt", false)
    }

    scenario("2-sat 4") {
      runScenario("data/2sat4.txt", false)
    }

    scenario("2-sat 5") {
      runScenario("data/2sat5.txt", true)
    }

    scenario("2-sat 6") {
      runScenario("data/2sat6.txt", true)
    }

  }
}
