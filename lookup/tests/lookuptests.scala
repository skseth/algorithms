import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import lookup._
 
class TwoSumSpec extends FeatureSpec with GivenWhenThen {

  def runScenario(filename:String, count:Int) = {
        given("a file with graphical input")

        when("we find 2Sums between -10000 and 10000")
        val twosums = twosum2.find_distinct_2sum(filename, -10000, 10000)        
   
        then("expected count is met")
        assert(twosums == count)
  }

  feature("2 Sum") {
    scenario("small twosum1") { runScenario("data/2SumSmall1.txt", 3) }
    scenario("small twosum2") { runScenario("data/2SumSmall2.txt", 5) }
    scenario("big collection") { runScenario("data/algo1-programming_prob-2sum.txt", 427) }
  }
}

class MedianMaintenanceSpec extends FeatureSpec with GivenWhenThen {

  def runScenario(filename:String, expected:Int) = {
        given(s"a file $filename with integer input")

        when("we maintain medians")
        val mediantotal = medianmaint.calculate(filename)        
   
        then(s"and the sum of medians module 10000 is as expected - $expected")
        assert(mediantotal == expected)
  }

  feature("2 Sum") {
    scenario("median maintenance 1") { runScenario("data/MedianSmall1.txt", 54) }
    scenario("median maintenance 2") { runScenario("data/MedianSmall2.txt", 148) }
    scenario("median maintenance big") { runScenario("data/Median.txt", 1213) }
  }
}