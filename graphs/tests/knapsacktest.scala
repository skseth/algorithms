import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

 
class KnapsackSpec extends FeatureSpec with GivenWhenThen {

  def readFromFile(filename:String):Knapsack = {
    var lines = scala.io.Source.fromFile(filename).getLines()
    var a = lines.next().split(" ")
    val ksweight = a(0).toInt
    val cweights = a(1).toInt
    val knapsack = new Knapsack(ksweight)

    for (wtidx <- 0 to cweights-1) {
        val l = lines.next()
        val lvs = l.split(" ")
        knapsack.addWeight(lvs(0).toInt, lvs(1).toInt)
    }

    knapsack
  }

  feature("knapsack - small problem") {

    scenario("knapsack - 4 ") {
        given("a knapsack and some weights")
        val knapsack = new Knapsack(6)
        knapsack.addWeight(3,4)
        knapsack.addWeight(2,3)
        knapsack.addWeight(4,2)
        knapsack.addWeight(4,3)

        when("optimal weights are calculated")
        knapsack.optimize()
        //knapsack.printSubproblems

        then("knapsack has expected value and weight")
        assert(knapsack.optimalValue == 8)
        assert(knapsack.optimalWeight == 5)
    }


    scenario("knapsack - pa 1") {
        given("a knapsack and some weights")
        val knapsack = readFromFile("data/knapsack1.txt")

        when("optimal weights are calculated")
        knapsack.optimize()
        //knapsack.printSubproblems

        then("knapsack has expected value and weight")
        assert(knapsack.optimalValue == 2493893)
        assert(knapsack.optimalWeight == 9976)
    }    

    scenario("knapsack - big") {
        given("a knapsack and some weights")
        val knapsack = readFromFile("data/knapsack_big.txt")

        when("optimal weights are calculated")
        knapsack.optimize()

        then("knapsack has expected value and weight")
        assert(knapsack.optimalValue == 4243395)
        assert(knapsack.optimalWeight == 1999783)
    }    
}
}