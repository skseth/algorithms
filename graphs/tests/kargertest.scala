import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

 
class KargerSpec extends FeatureSpec with GivenWhenThen {

  feature("MinCut using Kargers Algorithm") {
    info("I want to find min cut of a graph using a randomised algorithm")

    scenario("create a min cut from a small graph") {

        given("a file with graphical input")
        val h = Graph.fromFile("data/graphMinCutOfTwoPermuted.txt")

        when("karger's algo is run")
        val cuts = karger.mincut_repeat(h)
   
        then("the number of cuts is 2")
        assert(cuts == 2)

    }

    scenario("create a min cut from another small graph") {

        given("a file with graphical input")
        val h = Graph.fromFile("data/graphMinCutOfOne.txt")

        when("karger's algo is run")
        val cuts = karger.mincut_repeat(h)
   
        then("the number of cuts is one")
        assert(cuts == 1)

    }

    scenario("create a min cut from a slightly larger graph") {

        given("a file with graphical input")
        val h = Graph.fromFile("data/graphMinCutOfThree.txt")

        when("karger's algo is run")
        val cuts = karger.mincut_repeat(h)
   
        then("the number of cuts is three")
        assert(cuts == 3)

    }

    scenario("the big daddy") {

        given("a file with graphical input")
        val h = Graph.fromFile("data/kargerMinCut.txt")

        when("karger's algo is run")
        val cuts = karger.mincut_repeat(h)
   
        then("the number of cuts is 17")
        println(cuts)
        assert(cuts == 17)

    }
  }


}