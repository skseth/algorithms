package structs

import scala.collection.mutable._

class Matrix[T:Ordering](values:Array[Array[T]]) {
	def print() = {
		for (r <- 0 to values.length-1) {
			for (c <- 0 to values(r).length -1) {
				printf("%s ", values(r)(c))
			}
			println("")
		}
	}
}

object Matrix {

	def parseArray(line:String) = {
		val ret = new ArrayBuffer[Int]
	    val a = line.split("\\s+")
	    var s = a.map(y => ret += y.toInt)
	    ret.toArray
	}

	def fromFile(filename:String) = {
		var m = new ArrayBuffer[Array[Int]]()

		scala.io.Source.fromFile(filename).getLines().foreach{
		    x => m += parseArray(x)
		}
		new Matrix(m.toArray)
	}
}