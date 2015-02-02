
object findsecond {

  import scala.annotation._

  def second(n:List[Int]):Int = {
    case class Val(value:Int, cmp_vals:List[Int]) {
      def add(v:Val):Val = Val(value, v.value::cmp_vals)
    }

    def cmp(v1:Val, v2:Val) = if (v1.value > v2.value) v1.add(v2) else v2.add(v1)

    // n/2 + n/4 ... = n-1 comparisons
    def makelist(vals:List[Val]):List[Val] = (for (v <- vals grouped 2) yield cmp(v(0), v(1))).toList 

    @tailrec
    def findsecond(vals:List[Val]):Int = vals match {
      case(top::Nil) => top.cmp_vals.max // log n - 1 comparisons
      case _ => return findsecond(makelist(vals))
    }

    findsecond(n map {x => Val(x, Nil)})
  }

  def main(args:Array[String]) {
    println(second(List[Int](1,4,5,6,78,34,23,12)))
  }
}
