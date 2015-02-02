

object rselect {

	def rselect(a:Array[Int], stat:Int) = {
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

		def select(a:Array[Int], left:Int, right:Int, i:Int):Int = {
			if (left == right)
				a(left)
			else {
				val q = partition(a, a(left), left, right)
				if (q == i) a(q)
				else if (q > i) select(a, left, q-1, i)
				else select(a, q+1, right, i)
			}
		}

		select(a, 0, a.length-1, stat-1)
	}

	def main(args:Array[String]) {
		println(rselect(Array[Int](2,4,5,34,21,1,56,89,22), 5))
	}

}