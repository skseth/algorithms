import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

 
class ApspSpec extends FeatureSpec with GivenWhenThen {

  	def runScenario(filename:String, expectedCycles:Boolean, expectedShortestPath:Int) = {
        given("a graph")
        val f = floydwarshall.readFromFile(filename)

        when("APSP is computed using Floyd-Warshall Algo")
        f.apsp()
        val shortest = f.shortest_shortestpath
        val negCycles = f.hasNegativeCycles
        printf("APSP %s - %s %d\n", filename, negCycles, shortest)

        then("negative cycles are detected and shorted path computed")
        assert(negCycles == expectedCycles)
        assert(shortest == expectedShortestPath)  		
  	}

  feature("APSP - small problem") {

    scenario("apsp - small 1") {
    	runScenario("data/apsp1.txt", true, -1996682933)
    }

    scenario("apsp - small 2") {
    	runScenario("data/apsp2.txt", true, -2080343310)
    }

    scenario("apsp - small 3") {
    	runScenario("data/apsp3.txt", true, -19)
    }
  }
}