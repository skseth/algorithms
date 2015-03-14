import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

 
class DjikstraSpec extends FeatureSpec with GivenWhenThen {

  def runDjikstra(filename:String, startnode:Int, testNodes:Array[Int], expectedEstimates:Array[Int], expectedPreds:Array[Int]) = {
        given("a file with graphical input and a startnode")
        val g = Graph.fromFile(filename, true)
        when("djikstra's algo is run")

        val t0 = System.nanoTime()
        val nodes = djikstra.shortest_path(g, startnode)
        val t1 = System.nanoTime()
        println("Elapsed time: " + (t1 - t0) + "ns")
        then("estimates and predecessors are calculated as expected")
        assert(nodes.size == g.length)

        printf("estimates for %s\n", filename)

        var resp = ""
        for (n <- nodes.values.iterator) {
            resp += "," + n.estimate
        }
        println(resp)

        for (i <- 0 to expectedEstimates.length - 1) {
            val n = testNodes(i)
            val k = expectedEstimates(i)
            assert(nodes(n).estimate == k)
        }

        for (i <- 0 to expectedPreds.length - 1) {
            val n = testNodes(i)
            assert(nodes(n).pred == expectedPreds(i))
        }
  }

  feature("calculate shorted path for undirected graph via Djikstra's Algorithm") {

    scenario("shortest path search - small file 1") {
        runDjikstra("data/djikstra1.txt", 1, 
            Array[Int](1, 2, 3, 4),
            Array[Int](0, 3, 3, 5),
            Array[Int](1, 1, 1, 2)
        )
    }

    scenario("shortest path search - small file 2") {
        runDjikstra("data/djikstra2.txt", 1, 
            Array[Int](1, 2, 3, 4),
            Array[Int](0, 3, 4, 5),
            Array[Int](1, 1, 2, 2)
        )
    }

    scenario("shortest path search - big file") {
        runDjikstra("data/dijkstraData.txt", 1, 
            Array[Int](7,37,59,82,99,115,133,165,188,197),
            Array[Int](2599,2610,2947,2052,2367,2399,2029,2442,2505,3068),
            Array[Int]()
        )
    }
  }
}