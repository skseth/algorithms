import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._

 
class UnionFindSpec extends FeatureSpec with GivenWhenThen {

  feature("Union Find") {
    info("I want to store a partition of integers")

    scenario("Partition with 5 elements") {

        given("a list of 5 integers in a partition")
        val nodes = List(1,2,3,4,5)
        val part = Partition(nodes)

        when("two are joined")
        part.union(2,3)
   
        then("only 4 partitions remain")
        assert(part.size == 4)
    }
  }
}