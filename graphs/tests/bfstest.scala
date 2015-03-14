import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

 
class BFSSpec extends FeatureSpec with GivenWhenThen {

  feature("BFS Search") {
    info("I want to do BFS of a graph")

    scenario("Walk elements in bfs order") {

        given("a file with graphical input")
        val g = Graph.fromFile("data/graphMinCutOfTwo.txt")

        when("when we do BFS walk")
        bfs.walk(8, g)
   
        then("nothing happens")
        assert(true)

    }
  }
}


class DFSSpec extends FeatureSpec with GivenWhenThen {

  feature("DFS Search") {
    info("I want to do DFS of a graph")

    scenario("Walk elements in dfs order") {

        given("a file with graphical input")
        val g = Graph.fromFile("data/graphMinCutOfTwo.txt")

        when("when we do DFS walk")
        dfs.walk(8, g)
   
   		// tbd - make nicer
        then("nothing happens")
        assert(true)

    }
  }
}


class KosarajuSpec extends FeatureSpec with GivenWhenThen {

  def reportsccs(sccs:List[List[Int]]) = {

  	val rep = Array[Int](0,0,0,0,0)
  	val top5 = sccs take 5 

  	for (j <- 0 to top5.length-1) rep(j) = top5(j).length

  	rep.toList
  }

  def doKosarajuTest(filename:String, expected:List[Int]) = {
    given("a file with edge input")
    val g = Graph.fromEdgeFile(filename)

    when("when we apply Kosaraju Algorithm")
    val t0 = System.nanoTime()
    val sccs = dfs.kosaraju[Int](g)
    val t1 = System.nanoTime()
    println("Elapsed time: " + ((t1 - t0)/1000000) + "ms")

    then("we get sccs")
    println(reportsccs(sccs))

    assert(reportsccs(sccs) == expected)
  }

  feature("Kosaraju Algo") {
    info("I want to find SCCS of a directed graph")

    scenario("Small graph - SCC1") {
    	doKosarajuTest("data/graphEdgesForSCC1.txt", List(3,3,3,0,0))
	}

    scenario("Small graph - SCC2") {
    	doKosarajuTest("data/graphEdgesForSCC2.txt", List(3,3,2,0,0))
	}

    scenario("Small graph - SCC3") {
    	doKosarajuTest("data/graphEdgesForSCC3.txt", List(3,3,1,1,0))
	}

    scenario("Small graph - SCC4") {
    	doKosarajuTest("data/graphEdgesForSCC4.txt", List(7,1,0,0,0))
	}

    scenario("Small graph - SCC5") {
    	doKosarajuTest("data/graphEdgesForSCC5.txt", List(6,3,2,1,0))
	}

    scenario("Small graph - SCC6") {
    	doKosarajuTest("data/graphEdgesForSCC6.txt", List(35,7,1,1,1))
	}

    scenario("Big Daddy") {
    	doKosarajuTest("data/SCC.txt", List(434821, 968, 459, 313, 211))
	}
  }
}
