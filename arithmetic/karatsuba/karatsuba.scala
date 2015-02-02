object karatsuba {
  import scala.annotation._
  import scala.math._



  def karatsuba(x: Long, y: Long):Long = {

    @tailrec
    def toNumAcc(xrev:List[Char], scale:Int, acc:Long):Long = xrev match {
      case(Nil) => acc
      case(x::xs) => toNumAcc(xs, scale*10, acc + (x - '0')*scale)
    }

    def toNum(x:List[Char]) = { toNumAcc(x.reverse, 10, 0) }

    def add(x:List[Char], y:List[Char]):List[Char] = {
      val l = toNum(x) + toNum(y)
      return l.toString.toList
    }

    def karatsubaStr(x:List[Char], y:List[Char]):Long = {

      val x_size = x.length
      val y_size = y.length

      if ((x_size < 2) || (y_size < 2)) {
        return toNum(x) * toNum(y)
      }

      val m = Math.max(x_size, y_size) / 2

      val (x_h,x_l) = x splitAt m
      val (y_h,y_l) = y splitAt m
      println(x_h, x_l, y_h, y_l, m)

      val z0 = karatsubaStr(x_l, y_l)
      val z1 = karatsubaStr(add(x_l,x_h), add(y_l, y_h))
      val z2 = karatsubaStr(x_h, y_h)

      return (z2*10^(2*m))+((z1-z2-z0)*10^(m))+(z0)
    }

    return karatsubaStr(x.toString.toList, y.toString.toList)
    
  }

  def main(args:Array[String]) {
    println(karatsuba(203, 598012459))
  }

}

