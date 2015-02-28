import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import arithmetic._
import scala.collection.mutable._

class KaratsubaSpec extends FeatureSpec with GivenWhenThen {

  def runScenario(x:String,y:String,expected:String) = {
        given(s"two numbers $x and $y")

        when("when we do Karatsuba Multiplication")
        val z = karatsuba.mul(x,y)
   
        then(s"answer is $expected")
        assert(z == expected)    
  }

  feature("Karatsuba Multiplication") {

    scenario("two 4 digit numbers") {
        runScenario("5678","1234","7006652")
    }

    scenario("two numbers with different digits") {
        runScenario("134","46","6164")
    }

    scenario("wikipedia sample") {
        runScenario("12345","6789","83810205")
    }
    scenario("wikipedia sub-sample") {
        runScenario("345","789","272205")
    }
  }
}
