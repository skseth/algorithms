import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

 
class MinBottleneckSpec extends FeatureSpec with GivenWhenThen {

  def runScenario(filename:String, undirected:Boolean, start:Int, dest:Int, minb:Int, path:List[Int]) = {
  	    given(s"a file $filename with graphical input")
        val g = Graph.fromFile(filename, undirected)

        when(s"I look for the minbottleneck between $start and $dest")
        val (minb, bestpath) = minbottleneck.find(g, start, dest)

        then("I find the path with the minimum bottleneck.")
        assert(minb == minb)
        assert(bestpath == path)

  }

  feature("Min Bottleneck between two nodes of Graph") {
    info("The Min Bottleneck of a graph ")

    scenario("Small Directed Graph - 1") {
      runScenario("data/graphSmallWithPositiveLength.txt", false, 1, 4, 32, List(1,3,4))
    }

    scenario("Small Directed Graph - 2") {
    	runScenario("data/graphSmallWithTwoPathsToDest.txt", false, 1, 4, 2, List(1,3,2,4))
    }
  }
}
