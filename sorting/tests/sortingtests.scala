import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen

import scala.collection.mutable._
import sorting.qsort._

import org.scalatest.Tag

object SlowTest extends Tag("SlowTest")

trait SortBehaviors { this: FeatureSpec with GivenWhenThen =>
  type SortFn = IndexedSeq[Int] => Unit

  def readFileIntoIntArray(filename:String) = {
    val ab = new ArrayBuffer[Int]()
    scala.io.Source.fromFile(filename).getLines().foreach{x => ab += x.toInt}
    ab.toArray    
  }

  def isSorted(a:Array[Int]):Boolean = {
    for (i <- 1 to a.length-1) {if (a(i-1) > a(i)) return false}
    return true
  }

  def sortFileInputs(sort:SortFn, filename:String) {

        given("integers in a file")
        val al = readFileIntoIntArray(filename)

        when("quicksort is invoked with the array")
        sort(al.view.slice(0,al.length))
        println(al.toList)
   
        then("the array is returned sorted")
        assert(isSorted(al))
  }

  def sortScenarios(sort:SortFn) {

    scenario("10-integer array with choose first as pivot and simple partition") {
      sortFileInputs(sort,"data/10.txt") 
    }

    scenario("100-integer array with choose first as pivot and simple partition") {
      sortFileInputs(sort,"data/100.txt") 
    }

    scenario("1000-integer array with choose first as pivot and simple partition") {
      sortFileInputs(sort,"data/1000.txt") 
    }

    scenario("100000-integer array with choose first as pivot and simple partition", SlowTest) {
      sortFileInputs(sort,"data/QuickSort.txt") 
    }

  }
}

 
class QuicksortSpec extends FeatureSpec with GivenWhenThen with SortBehaviors {

  feature("quicksort - choose first, simple partition") {
    def sortfn(a:IndexedSeq[Int]):Unit = sorting.qsort.quicksort[Int](choose_first, simple_partition)(a)
    info("I want to sort integer arrays using quicksort")

    sortScenarios(sortfn)
  }

  feature("quicksort - choose last, simple partition") {
    def sortfn(a:IndexedSeq[Int]):Unit = sorting.qsort.quicksort[Int](choose_last, simple_partition)(a)
    info("I want to sort integer arrays using quicksort")

    sortScenarios(sortfn)
  }

  feature("quicksort - choose median_of_three, simple partition") {
    def sortfn(a:IndexedSeq[Int]):Unit = sorting.qsort.quicksort[Int](choose_median_of_three, simple_partition)(a)
    info("I want to sort integer arrays using quicksort")

    sortScenarios(sortfn)
  }

  feature("quicksort - choose first, hoare partition - iterative") {
    def sortfn(a:IndexedSeq[Int]):Unit = sorting.qsort.quicksort[Int](choose_first, hoare_partition_iter)(a)
    info("I want to sort integer arrays using quicksort")

    sortScenarios(sortfn)
  }


}