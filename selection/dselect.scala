

object dselect {

	def dselect(a:Array[Int], stat:Int) = {
		import scala.annotation._

		def swap(a:Array[Int],l:Int, r:Int) = {
			val t = a(l)
			a(l) = a(r)
			a(r) = t
		}

		@tailrec
		def exhaustLarger(a:Array[Int], pivot:Int, larger:Int):Int = if (a(larger) > pivot) exhaustLarger(a,pivot,larger-1) else larger

		@tailrec
		def exhaustSmaller(a:Array[Int], pivot:Int, smaller:Int):Int = if (a(smaller) < pivot) exhaustSmaller(a,pivot,smaller+1) else smaller

		@tailrec
		def partition(a:Array[Int], pivot:Int, left:Int, right:Int):Int = {
			val larger = exhaustLarger(a,pivot,right)
			val smaller = exhaustSmaller(a,pivot,left)

			if (smaller < larger) {
				swap(a,smaller,larger)
				partition(a,pivot,smaller,larger)
			}
			else {
				smaller
			}
		}

		@tailrec
		def bubblesort(a:Array[Int], left:Int, right:Int):Unit = {
			var swapped = false
			for (i <- left+1 until right) {
				if (a(i-1) > a(i)) {
					swap(a,i-1,i)
					swapped = true
				}
			}

			if (!swapped) {
				return
			}
			else {
				bubblesort(a, left, right-1)
			}
		}

		@tailrec
		def insertionsort(a:Array[Int],left:Int, right:Int, cur:Int):Unit = {
			if (cur <= right) {
				var j = cur
				while (j > left && a(j-1) > a(j)) {
					swap(a,j,j-1)
					j = j - 1
				}
				insertionsort(a, left, right, cur+1)				
			}
		}

		def divide_and_sort(a:Array[Int], left:Int, right:Int, divider:Int) = {
			for (i <- left to right by divider) {
				insertionsort(a,i,Math.min(right,i+divider-1), i+1)
			}
		}

		def median(a:Array[Int], left:Int, right:Int) = a(left + ((right - left)/2))

		def medians(a:Array[Int], left:Int, right:Int, divider:Int):Array[Int] = {

			var len = ((a.length - 1)/ divider) + 1
			var newa = new Array[Int](len)

			var l = 0
			for (i <- 0 to len-2) {
				newa(i) = median(a,l, l+divider-1)
				l = l + divider
			}

			newa(len-1) = median(a, l, right)

			newa
		}

		def dselect(a:Array[Int], left:Int, right:Int, i:Int):Int = {
			if (right == left) return a(left)
			divide_and_sort(a,left,right,5)
			val c = medians(a, left, right, 5)
			val len = right - left + 1
			val pivot = dselect(c, 0, c.length - 1, len / 10)
			val q = partition(a,pivot, left, right)

			if (q == i) a(i)
			else if (q > i) dselect(a, left, q-1, i)
			else dselect(a, q+1, right, i)
		}

		dselect(a, 0, a.length-1, stat-1)
	}

	def main(args:Array[String]) {
		println(dselect(Array[Int](2,4,5,34,21,1,56,89,22), 5))
	}

}