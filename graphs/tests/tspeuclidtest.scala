import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._

 
class TspEuclidSpec extends FeatureSpec with GivenWhenThen {

    def runScenario(filename:String, expectedMinTourLength:Float) = {
        given("a list of cities with x,y coordinates")
        val tsp = tsp_euclid.readFromFile(filename)

        when("min tour length is computed")
        val mintourlength = tsp.minTour()

        //for (i <- tsp.vertices(12)) println(i)

        then("expected min tour length is computed")
        assert(mintourlength == expectedMinTourLength)        
    }

  feature("TSP") {

    scenario("tsp - small 1") {
        runScenario("data/tspsmall.txt", 60.0f)
    }

    scenario("tsp - big") {
        runScenario("data/tsp.txt", 25.0f)
    }

  }
}