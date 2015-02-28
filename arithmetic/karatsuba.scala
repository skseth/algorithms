package arithmetic

object karatsuba {
  import scala.annotation._
  import scala.math._

  class KNumber(val s:String, val b:BigInt) {
    def length = s.length

    def splitAt(m:Int) = {
      val (a,b) = s splitAt (length - m)
      (KNumber(a), KNumber(b))
    }

    def mul(y:KNumber) = KNumber(this.b * y.b)

    def add(y:KNumber) = KNumber(this.b + y.b)

    def sub(y:KNumber) = KNumber(this.b - y.b)

    def mul10(m:Int) = KNumber(this.b * BigInt(10).pow(m))

    override def toString = s
  }

  object KNumber {
    def apply(s:String) = new KNumber(s, if (s.length > 0) BigInt(s) else 0)
    def apply(b:BigInt) = new KNumber(b.toString, b)
  }
  
  def mul(x: String, y: String):String = {

    def karatsubaInner(x:KNumber, y:KNumber):KNumber = {
      println(s"kinner - $x * $y")
      if (x.length < 2 || y.length < 2) return x.mul(y)

      val m = max(x.length, y.length)
      val m2 = m - (m/2)

      val (highx, lowx) = x.splitAt(m2)
      val (highy, lowy) = y.splitAt(m2)

      val z0 = karatsubaInner(lowx, lowy)
      val z1 = karatsubaInner(lowx.add(highx), lowy.add(highy))
      val z2 = karatsubaInner(highx, highy)

      z2.mul10(2*m2).add(z1.sub(z2).sub(z0).mul10(m2)).add(z0)
    }

    val k = karatsubaInner(KNumber(x), KNumber(y))
    k.s
  }

}

