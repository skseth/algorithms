// see also http://algs4.cs.princeton.edu/23quicksort/
package sorting
object qsort {

	import scala.annotation._
//	import structs._
	import scala.math._
	import scala.collection.mutable._

	type ChoosePivotFunction[T] = IndexedSeq[T] => T 
	type PartitionFunction[T] = (IndexedSeq[T], T) => (Int,Int)

	//quickSort[K](a: Array[K])(implicit : math.Ordering[T]): Unit
	def quicksort[T](cf: ChoosePivotFunction[T], pf: PartitionFunction[T])(a:IndexedSeq[T]) = {
		sort(a)

		def sort(s:IndexedSeq[T]):Unit = {
			if (s.length > 1) {
				val (p_l,p_r) = pf(s, cf(s))
				sort(s.view.slice(0,p_l+1))
				sort(s.view.slice(p_r,s.length))
			}
		}		
	}


	def swap[T](a:IndexedSeq[T], i:Int, j:Int) = {
		val t = a(i)
		a(i) = a(j)
		a(j) = t
	}

	def choose_first[T](s:IndexedSeq[T]):T = {
		s(0)
	}

	def choose_last[T](s:IndexedSeq[T]):T = {
		swap[T](s,0, s.length - 1)
		s(0)
	}

	def choose_median_of_three[T](s:IndexedSeq[T])(implicit ord: Ordering[T]):T = {
		import ord._

		val center = s.length / 2

		if (center == 0) return choose_first(s)

		var ms = Array[Int](0, center, s.length-1)

		if (s(ms(0)) > s(ms(1)) ) { swap[Int](ms,0,1) }
		if (s(ms(0)) > s(ms(2)) ) { swap[Int](ms,0,2) }
		if (s(ms(1)) > s(ms(2)) ) { swap[Int](ms,1,2) }	

		swap[T](s, 0,ms(1))
		return s(0)
	}

	// partition functions

	/*
	Hoare-Partition(A, p, r)
	x = A[p]
	i = p - 1
	j = r + 1
	while true
	    repeat
	        j = j - 1
	    until A[j] <= x
	    repeat
	        i = i + 1
	    until A[i] >= x
	    if i < j
	        swap( A[i], A[j] )
	    else
	        return j
	*/

	def hoare_partition[T](s:IndexedSeq[T], pivot:T)(implicit ord: Ordering[T]):(Int,Int) = {
		import ord._

		@tailrec
		def hoare_partition_acc(s:IndexedSeq[T], offset:Int):Int = {
			val i = (s takeWhile{x => x < pivot}).foldLeft(0) { (l,_) => l + 1}
			val j = (s.reverseIterator takeWhile { x => x > pivot}).foldLeft(s.length-1) {(l,_) => l - 1}

			if (i < j) {
				swap(s, i,j)
				hoare_partition_acc(s.view.slice(i,j+1), offset+i)
			}
			else {
				offset+i
			}
		}

		val pivotidx = hoare_partition_acc(s, 0)
		(pivotidx-1,pivotidx+1)

	}


	def hoare_partition_iter[T](s:IndexedSeq[T], pivot:T)(implicit ord: Ordering[T]):(Int,Int) = {
		import ord._

		var i = 0
		var j = s.length-1

		while (true) {
			while(s(j) > pivot) {
				j = j - 1
			}
			while (s(i) < pivot) {
				i = i + 1
			}

			if (i < j) {
				swap(s,i,j)
			}
			else {
				return (j-1,j+1)
			}
		}

		return (0,0)

	}

	def simple_partition[T](s:IndexedSeq[T], pivot:T)(implicit ord: Ordering[T]):(Int,Int) = {
		import ord._
        var i = 1
        println("par pivot ", pivot, " ", s.toList)
        for (j <- i to s.length - 1) {
        	if (s(j) < pivot) {
        		swap[T](s, i,j)
        		i = i + 1
        	}
        }

        swap[T](s, i-1, 0)
        println("  parend ", s.toList, " ", i-1)
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
	def exhaustLarger[T](s:IndexedSeq[T], pivot:T, larger:Int)(implicit ord: Ordering[T]):Int 
			= if (ord.compare(s(larger),pivot) > 0) exhaustLarger(s,pivot,larger-1) else larger

	@tailrec
	def exhaustSmaller[T](s:IndexedSeq[T], pivot:T, smaller:Int)(implicit ord: Ordering[T]):Int 
			= if (ord.compare(s(smaller), pivot) < 0) exhaustSmaller(s,pivot,smaller+1) else smaller


}

