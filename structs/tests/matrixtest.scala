import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import scala.collection.mutable._

 
class MatrixSpec extends FeatureSpec with GivenWhenThen {

  feature("Read Matrix From File Into Graph") {

    scenario("read matrix from file") {

        given("a file with matrix input")
        val datafile = "data/smallMatrix.txt"

        when("file is read")
        val m = Matrix.fromFile(datafile)
   
        then("matrix is read with correct values")
        m.print()
    }
  }
}