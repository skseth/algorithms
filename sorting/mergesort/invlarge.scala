object inversions {

  import scala.annotation._

  def inversions(xs: List[Int]): Long = {

    case class InvList(v:List[Int], inv:Long = 0)

    def sort(xs: InvList) : InvList = {
      if (xs.v.length < 2) 
        return xs
      else {
        val (left, right) = xs.v splitAt(xs.v.length / 2)
        merge(sort(InvList(left)), sort(InvList(right)))
      }
    }

    def merge(l:InvList, r:InvList) : InvList = {
      mergeAcc(l.v,r.v,List[Int](), l.inv + r.inv)
    }

    @tailrec
    def mergeAcc(l:List[Int], r:List[Int], accL:List[Int], accN:Long) : InvList = (l, r) match {
      case(Nil, ys) => InvList(accL.reverse ::: r, accN)
      case(xs, Nil) => InvList(accL.reverse ::: l, accN)
      case(x :: xs, y :: ys) if x <= y => mergeAcc(xs, r, x::accL, accN)
      case(x :: xs, y :: ys) if y < x => mergeAcc(l, ys, y::accL, accN + l.length)
    }

    sort(InvList(xs)).inv

  }

  def main(args:Array[String]) {

    var list = List[Int]()
    scala.io.Source.fromFile("integerArray.txt").getLines().foreach{x => list = (x.toInt)::list}

    println(inversions(list.reverse))

    //val lints = List[Int]( 9, 12, 3, 1, 6, 8, 2, 5, 14, 13, 11, 7, 10, 4, 0 )

    //println(inversions(lints))
  }

}

