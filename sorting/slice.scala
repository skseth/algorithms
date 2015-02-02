object slice {

	case class Slice(a:Array[Int], left:Int, right:Int) {
		def apply(i: Int) = a(left + i)
		def length = right - left
		def swap(i:Int, j:Int) = {
			val t = a(left+i)
			a(i) = a(left+j)
			a(j) = t
		}
	}


	def main(args:Array[String]) {
		val a = Array[Int](1,3,4,6,7)

		val s = Slice(a,2,4)

		println(s(2))
	}

}