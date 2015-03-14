package sorting

import scala.collection._
import scala.annotation._

object merge {

  def sort[C <: TraversableLike[_, C]](xs: C)(implicit merge:(C,C) => C):C = {
    if (xs.size < 2) 
      xs
    else {
      val (left, right) = xs splitAt(xs.size / 2)
      merge(sort(left), sort(right))
    }
  }

  implicit def merge[T](l:List[T], r:List[T])(implicit ord:Ordering[T]) : List[T] = {
    import ord._
    @tailrec
    def mergeAcc(l:List[T], r:List[T], accL:List[T]) : List[T] = (l, r) match {
      case(Nil, ys) => accL.reverse ::: r
      case(xs, Nil) => accL.reverse ::: l
      case(x :: xs, y :: ys) if x <= y => mergeAcc(xs, r, x::accL)
      case(x :: xs, y :: ys) if y < x => mergeAcc(l, ys, y::accL)
    }

    mergeAcc(l,r,List[T]())
  }

}

object inversions {

  import scala.annotation._

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

  def count(xs: List[Int]):Long = {
    sort(InvList(xs)).inv
  }
}



