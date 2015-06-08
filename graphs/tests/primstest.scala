import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

 
class PrimsSpec extends FeatureSpec with GivenWhenThen {

  def runPrims(filename:String, expectedCost:Long) = {
        given("a file with graphical input and a startnode")
        val g = Graph.fromEdgeFileV2(filename, true)

        when("prim's algo is run")
        val cost = prims.mst_size(g)

        then("mst is calculated correctly")
        assert(cost == expectedCost)
  }

  feature("calculate cost of MST using Prim's algorithm") {

    scenario("small file 1") {
        runPrims("data/primssmall1.txt", 6)
    }
    scenario("small file 2") {
        runPrims("data/primssmall2.txt", -16)
    }
    scenario("big file") {
        runPrims("data/primsbig.txt", -3612829)
    }
  }
}