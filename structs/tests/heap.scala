import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import scala.collection.mutable._
import structs._

 
class HeapSpec extends FeatureSpec with GivenWhenThen {

  feature("Heapify") {

    scenario("convert a node with heapified sub-trees to a heap") {
        given("an array of ints")
        var a = ArrayBuffer[Int](16,4,10,14,7,9,3,2,8,1)
        val expected = ArrayBuffer[Int](16,14,10,8,7,9,3,2,4,1)

        when("when we heapify ")
        val a_heap = new heap.ArrayBufferHeap(a)
        a_heap.heapify(1)
   
        then("the sub-tree at index 1 is heapified")
        assert(a.sameElements(expected))
    }

    scenario("a min heap") {
        given("an array of ints")
        var a = ArrayBuffer[Int](10,2,1)
        val expected = ArrayBuffer[Int](1,2,10)

        when("when we heapify ")
        val a_heap = new heap.ArrayBufferHeap[Int](a)(implicitly[Ordering[Int]].reverse)
        a_heap.heapify(0)
   
        then("lowest element is at top")
        assert(a.sameElements(expected))
    }
  }
}