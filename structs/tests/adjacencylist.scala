import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import scala.collection.mutable._

 
class AdjacencyListSpec extends FeatureSpec with GivenWhenThen {

  feature("Read AdjacencyList From File Into Graph") {
    info("I want read an adjacency list")

    scenario("read adjacency list from file") {

        given("a file with graphical input")
        val datafile = "data/kargerMinCut.txt"

        when("file is read")
        val h = Graph.fromFile(datafile)
   
        then("200 nodes are read")
        assert(h.length == 200)
        h.print()
    }

    scenario("read adjacency list from edge file") {

        given("a file with graphical input")
        val datafile = "data/graphEdgesForSCC1.txt"

        when("file is read")
        val g = Graph.fromEdgeFile(datafile)
   
        then("9 nodes are read")
        assert(g.length == 9)
        g.print()
    }
  }

  feature("Combine Nodes in a Graph") {

    scenario("combine nodes in a graph") {

        given("a graph with 5 nodes")
        val datafile = "data/graphSmall.txt"
        val g = Graph.fromFile(datafile)
        val orig_length = g.length
 
        when("when 2 nodes are joined")
        g.join(2,3)

        then("number of nodes reduces by one, self edges eliminated, and references to deleted node removed")
        assert(g.length == orig_length - 1)

        then("no node should point to 3")
        // no node should have an edge pointing to 3, the deleted node
        assert(g.allNodes.values.forall(n => !n.allEdges.exists(e => e == 3)))

    }
  }

  feature("Randomly pick an edge") {
    scenario("get a random edge") {
        given("a graph with 5 nodes")
            val datafile = "data/graphSmall.txt"
            val g = Graph.fromFile(datafile)

        when("when a random edge is picked")
            val (from,to) = g.randomEdge()

        then("the edge exists")
            assert(g.allNodes()(from).allEdges.exists(e => e == to))
    }
   }

}