package sorting
object mergesort {

  def mergesort(xs: List[Int]): List[Int] = {
    def sort(xs: List[Int]) : List[Int] = {
      if (xs.length < 2) 
        return xs
      else {
        val (left, right) = xs splitAt(xs.length / 2)
        merge(sort(left), sort(right))
      }
    }

    def merge(l:List[Int], r:List[Int]) : List[Int] = (l, r) match {
        case(Nil, ys) => ys
        case(xs, Nil) => xs
        case(x :: xs, y :: ys) if x <= y => x::merge(xs, r)
        case(x :: xs, y :: ys) if y < x => y::merge(l, ys)
    }

    sort(xs)
  }
}

