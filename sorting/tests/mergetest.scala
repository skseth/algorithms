import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen

import scala.collection.mutable._


class MergesortSpec extends FeatureSpec with GivenWhenThen with SortBehaviors {

  feature("mergesort - Lists") {
	import sorting.merge._
    scenario("10-integer array with choose first as pivot and simple partition") {
      given("integers in a List")
      val in = List(9,4,5,12,2,34)

      when("mergesort is invoked with the list")
      val out = sort(in)
 
      then("the array is returned sorted")
      assert(out == List(2,4,5,9,12,34))
    }
  }

  feature("Inversions") {
	import sorting.inversions._
    scenario("Count number of inversions in an array") {

      given("integers in a List")
      val in = List(1,2,4,3,5,6)

      when("mergesort is invoked with the list")
      val out = count(in)
 
      then("the array is returned sorted")
      assert(out == 1)
    }

    scenario("Count number of inversions from file") {
	  given("list of integers in a file")
	  var list = List[Int]()
	  scala.io.Source.fromFile("data/integerArray.txt").getLines().foreach{x => list = (x.toInt)::list}

      when("mergesort is invoked with the list")
      val out = count(list.reverse)
 
      then("the array is returned sorted")
      assert(out == 2407905288L)
    }
  }
}

 