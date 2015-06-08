package graphs
import scala.annotation._
import structs._
import scala.collection.mutable._
import scala.util.Sorting._


class tsp_euclid(cnodes:Int) {
	val nodes = new Array[(Float,Float)](cnodes)
	val d = Array.ofDim[Float](cnodes,cnodes)

	def calc_distance(i:Int,j:Int) = Math.sqrt(Math.pow(nodes(i)._1-nodes(j)._1,2) + Math.pow(nodes(i)._2-nodes(j)._2, 2)).toFloat

	def addNode(i:Int, x:Float, y:Float) = {
		nodes(i) = (x,y)
		for (j <- 0 to i-1) {
			d(i)(j) = calc_distance(i,j)
			d(j)(i) = d(i)(j)
		}
	}

	val A = Array.ofDim[Float](1 << (cnodes-1),cnodes-1)

    def sridx(sidx:Int) = sidx >> 1

    def setA(s:Int, j:Int, v:Float) = {
    	A(sridx(s))(j-1) = v
    }

    def getA(s:Int, j:Int) = if (s == 1 && j == 0) 0 
                            else if (j == 0) Float.MaxValue
                            else A(sridx(s))(j-1)

    def subsets(size:Int):Stream[Int] = {
    	val adjsize = size-1
    	def inc(value:Int) = value << 1 | 1
    	def firstsubset(size:Int, value:Int):Int = if (size == 0) value else firstsubset(size-1,inc(value))

    	// gosper's hack! http://www.cl.cam.ac.uk/~am21/hakmemc.html#item175
    	def nextsubset(a:Int) = {
		  val c = (a & -a)
		  val r = a+c
		  (((r ^ a) >> 2) / c) | r
    	}

    	val first = firstsubset(adjsize,0)
    	val last = first << (cnodes - size)

    	def loop(cur:Int, last:Int):Stream[Int] = if (cur == last) Stream.empty else {
    		val nextval = nextsubset(cur) 
    		inc(nextval) #:: loop(nextval, last)
        }


    	inc(first) #:: loop(first, last)
    }

    def vertices(s:Int):Stream[Int] = {
    	@annotation.tailrec
    	def skipvalues(value:Int, nextidx:Int):(Int,Int) = 
    			if ((value & 1) == 0) skipvalues(value >> 1, nextidx + 1)
    			else (value, nextidx)

    	def loop(value:Int, nextidx:Int):Stream[Int] = value match {
	    		case 0 => Stream.empty
	    		case value => {
	    			val (newval, newidx) = skipvalues(value, nextidx)
	    			newidx #:: loop(newval >> 1, newidx+1)
	    		}
     	}

     	loop(s, 0)
    }

    def setminus(s:Int, j:Int) = if (j == 0) (s ^ 1) else {s ^ (1 << j)}

    def minForNode(s:Int, j:Int) = {
        val snoj = setminus(s,j)

        val retval = vertices(snoj).foldLeft(Double.MaxValue) { (z,k) => {Math.min(z, getA(snoj, k) + d(j)(k))}}

        retval.toFloat
    }

    def minTour():Float = {
    	for { m <- 2 to cnodes
    		  s <- subsets(m)
    		  j <- vertices(setminus(s,0)) 
    		} {
                val retval = minForNode(s,j)
    			setA(s, j, retval)
                //println(s"mintour ${s.toBinaryString} $j $retval")
    	}

        // all nodes
        val alls = (1 << cnodes) - 1

        val (minval, minnode) = vertices(setminus(alls,0)).foldLeft((Double.MaxValue,0)) { 
            case ((min,minnode),node) => {
                val newdist = getA(alls,node) + d(node)(0)
                if (newdist < min) (newdist,node) else (min,minnode)
            }
        }

        println(s"minval $minval minnode $minnode")

        minval.toFloat
    }


/*
A[S,1] = 0 if S = {1}
       = inf, otherwise

For m = 2,3,..n
  for each set S inclin {1,2,..n} of size m that contains i
     for each j in S, j <> 1
        A[Sij] = min (k in S, k <> j) A[S - {j}, k]

return min(j=2 to n) A[{1,2,..n},j] + cj1

*/


}

object tsp_euclid {
	
	def readFromFile(filename:String) = {
		var lines = scala.io.Source.fromFile(filename).getLines()

		var l = lines.next().split(" ")
		var cNodes = l(0).toInt
		val tsp = new tsp_euclid(cNodes)

		for (i <- 0 to cNodes-1) {
			val l = lines.next()
			val a = l.split(" ")
			tsp.addNode(i, a(0).toFloat, a(1).toFloat)
		}

		tsp
	}

}