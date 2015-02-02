// see also http://algs4.cs.princeton.edu/23quicksort/


object quicksort {
	import scala.annotation._
	import scala.collection.mutable._


	def quicksort(a:Array[Int]) = {
		sort(Slice(a, 0, a.length - 1),simple_partition,choose_first)

		def sort(s:Slice, pf: PartitionFunction, cf: ChoosePivotFunction):Unit = {
			if (s.length > 1) {
				val (p_l,p_r) = pf(s, cf(s))
				sort(s.slice(0,p_l), pf, cf)
				sort(s.slice(p_r,s.length-1), pf, cf)
			}
		}		

		// choosepivot functions

		type ChoosePivotFunction = Slice => Int 

		def choose_first(s:Slice):Int = {
			s(0)
		}

		def choose_last(s:Slice):Int = {
			s.swap(0, s.length - 1)
			s(0)
		}

		def choose_median_of_three(s:Slice):Int = {
			val center = s.length / 2

			if (center == 0) return choose_first(s)

			var ms = Slice(Array[Int](0, center, a.length-1),0,2)

			if (a(ms(0)) > a(ms(1))) { ms.swap(0,1) }
			if (a(ms(0)) > a(ms(2))) { ms.swap(0,2) }
			if (a(ms(1)) > a(ms(2))) { ms.swap(1,2) }	

			s.swap(0,ms(1))
			return s(0)
		}

		// partition functions
		type PartitionFunction = (Slice, Int) => (Int,Int) 

		@tailrec
		def hoare_partition(s:Slice, pivot:Int):(Int,Int) = {
			val larger = exhaustLarger(s,pivot,a.length-1)
			val smaller = exhaustSmaller(s,pivot,0)

			if (smaller < larger) {
				s.swap(smaller,larger)
				hoare_partition(s.slice(smaller,larger),pivot)
			}
			else {
				(smaller-1,smaller+1)
			}
		}

		def simple_partition(s:Slice, pivot:Int):(Int,Int) = {
	        var i = 1

	        for (j <- i to s.length - 1) {
	        	if (s(j) < pivot) {
	        		s.swap(i,j)
	        		i = i + 1
	        	}
	        }

	        s.swap(i-1, 0)

	        // pivot is now at i - 1, its rightful place
	        return (i - 2, i)
		}

/*
		@tailrec
		def three_way_partition(a:Array[Int], pivot:Int, left:Int, right:Int):(Int,Int) = {
	        var lt = left
	        var gt = left;
	        var i = lo;
	        while (i <= gt) {
	            if (a[i] < pivot) {
	            	swap(a, lt, i)
	            	lt = lt + 1
	            	i = i + 1
	            }
	            else if (a[i] > pivot) {
	            	swap(a, i, gt)
	            	gt = gt - 1
	            }
	            else i = i + 1
	        }

	        return (lt - 1, gt + 1)
		}	
*/


		@tailrec
		def exhaustLarger(s:Slice, pivot:Int, larger:Int):Int 
				= if (s(larger) > pivot) exhaustLarger(s,pivot,larger-1) else larger

		@tailrec
		def exhaustSmaller(s:Slice, pivot:Int, smaller:Int):Int 
				= if (s(smaller) < pivot) exhaustSmaller(s,pivot,smaller+1) else smaller

		// helper classes

		case class Slice(a:Array[Int], left:Int, right:Int) {
			def apply(i: Int) = a(left + i)
			def length = right - left + 1
			def swap(i:Int, j:Int) = {
				val t = a(left+i)
				a(left+i) = a(left+j)
				a(left+j) = t
			}
			def slice(l:Int, r:Int):Slice = new Slice(a, left+l, left+r)
		}
	}

	def main(args:Array[String]) {
	    val ab = new ArrayBuffer[Int]()
	    scala.io.Source.fromFile("100.txt").getLines().foreach{x => ab += x.toInt}
	    var al:Array[Int] = ab.toArray
	    quicksort(al)
	    println(al.toList)
	}

}