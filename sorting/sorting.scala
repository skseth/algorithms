package object sorting {
	import sorting.qsort._
	import sorting.mergesort._
	import sorting.inversions._
	import scala.collection.mutable._

	def quicksort(a:Array[Int]) = sorting.qsort.quicksort[Int](choose_first, simple_partition)(a) 

	def main1(args:Array[String]) {

    	println(mergesort(List(34,56,7,90,7,30)))
  	}

	def main(args:Array[String]) {
	   val ab = new ArrayBuffer[Int]()
	   scala.io.Source.fromFile("data/10.txt").getLines().foreach{x => ab += x.toInt}
	   var al:Array[Int] = ab.toArray
	   quicksort(al)
	   println(al.toList)
	}

	def main2(args:Array[String]) {
	    var list = List[Int]()
	    scala.io.Source.fromFile("integerArray.txt").getLines().foreach{x => list = (x.toInt)::list}

	    println(inversions(list.reverse))

	    //val lints = List[Int]( 9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0 )

	    //println(inversions(lints))
	}
}
