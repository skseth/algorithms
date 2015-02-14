import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._

 
class NumWithDigitsSpec extends FeatureSpec with GivenWhenThen {

  feature("Numeric String with Digits") {
    info("I want to represent large numbers as string")

    scenario("create a NumString and get integer value") {
        import structs.NumWithDigits._

        def add[T](v1:T,v2:T)(implicit adder:NumWithDigits[T]) = adder.add(v1,v2)

        given("a numeric string")
        val s = "34343948"

        when("add 5 and convert to int")
        val i = add(s, "5").toInt
   
        then("the value of i is 5 greater")
        assert(i == 34343953)
    }
  }
}