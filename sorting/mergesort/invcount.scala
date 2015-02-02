object inversions {


  def inversions(xs: List[Int]): List[(Int,Int)] = {

    case class InvList(v:List[Int], inv:List[(Int,Int)] = Nil) {

      def Prepend(x:Int) = InvList(x::v, inv)

      def PrependWithInversion(y:Int, xs:List[Int]) = InvList(y::v, (xs map {x => (x,y)}) ++ inv )
    }

    def sort(xs: InvList) : InvList = {
      if (xs.v.length < 2) 
        return xs
      else {
        val (left, right) = xs.v splitAt(xs.v.length / 2)
        merge(sort(InvList(left)), sort(InvList(right)))
      }
    }

    def merge(l:InvList, r:InvList) : InvList = (l.v, r.v) match {
        case(Nil, ys) => r
        case(xs, Nil) => l
        case(x :: xs, y :: ys) if x <= y => merge(InvList(xs, l.inv), r).Prepend(x)
        case(x :: xs, y :: ys) if y < x => merge(l, InvList(ys, r.inv)).PrependWithInversion(y, l.v)
    }

    sort(InvList(xs)).inv

  }

  def main(args:Array[String]) {

    var list = List[Int]()
    scala.io.Source.fromFile("integerArray.txt").getLines().foreach{x => list = (x.toInt)::list}

    println(inversions(list.reverse))
  }

}

