package graphs
import structs._
import scala.collection.mutable._
import scala.util.Sorting._


class BitCluster(values:Array[HashSet[Int]]) {
	val cbits = values.length
	val kruskals = new Kruskals();

	def hammingDistance(v1:Int, v2:Int) = {
		var dist = 0
		var xor_val = v1 ^ v2
		while (xor_val != 0) {
			dist += 1
			xor_val &= xor_val - 1
		}

		dist
	}

	def addNodesToKruskals(bit:Int) = {
		val bitValues = values(bit)
		for (node <- bitValues) {
			kruskals.addNode(node)
		}		
	}

	def bit_iszero(value:Int, mask:Int) = (value & mask) == 0
	def bit_set(value:Int, mask:Int) = value | mask
	def bit_unset(value:Int, mask:Int) = (value ^ (mask & value))

	def addEdgeIfValueExists(value:Int, luvalue:Int, distance:Int, bit:Int) = {
//		if (distance != hammingDistance(value, luvalue)) {
//			printf("*** ERROR *** %s-%s exp - %d, actual - %d\n", value.toBinaryString, luvalue.toBinaryString, hammingDistance(value, luvalue), distance)
//			printf("*** Edge - n1 %d n2 %d dist %d bit %d\n", value, luvalue, distance, bit)
//			throw new Exception("hamming distance wrong!")
//		}
//		else {
//		    printf("+++ Edge - n1 %d n2 %d dist %d bit %d\n", value, luvalue, distance, bit)			
//		}

		if (bit < cbits) {	
			if (values(bit)(luvalue)) {
//				printf("Edge - n1 %d n2 %d dist %d bit %d\n", value, luvalue, distance, bit)
				kruskals.addEdge(value, luvalue, distance)
			}
		}
	}

	def addEdgesForNode(value:Int, bit:Int) = {
		var mask = 1
		for (i <- 0 to cbits-1) {
			if (bit_iszero(value,mask)) {

				val nextbitval = bit_set(value,mask)
				addEdgeIfValueExists(value, nextbitval, 1, bit+1)
				var maskj = 1 << (i+1)
				for (j <- i+1 to cbits-1) {
					if (bit_iszero(value, maskj)) {
						val next2bitval = bit_set(nextbitval, maskj)
						addEdgeIfValueExists(value, next2bitval, 2, bit+2)
					}
					maskj <<= 1
				}
			}
			else {

				val thisbitbaseval = bit_unset(value,mask)
				var maskj = 1
				for (j <- 0 to cbits-1) {
					if (bit_iszero(value,maskj)) {
						val thisbitval = bit_set(thisbitbaseval, maskj)
						addEdgeIfValueExists(value, thisbitval, 2, bit)
					}
					maskj <<= 1
				}
			}
			mask <<= 1
		}

		//values(bit).remove(value)
	}

	def addEdgesToKruskals(bit:Int) = {
		val bitValues = values(bit)
		for (value <- bitValues) {
			addEdgesForNode(value, bit)
		}		
	}

	def partition() = {

		for ( i <- 0 to cbits-1) {
			addNodesToKruskals(i)
		}

		for ( i <- 0 to cbits-1) {
			addEdgesToKruskals(i)
		}

		kruskals.computeMST()

		kruskals.nodes.size
	}
}

object BitCluster {

	def fromBitFile(filename:String) = {
		var lines = scala.io.Source.fromFile(filename).getLines()
		var a = lines.next().split(" ")
		val cNodes = a(0).toInt
		val cBits = a(1).toInt
		val powersoftwo = new Array[Int](cBits)
		powersoftwo(0) = 1
		for (i <- 1 to cBits-1) {
			powersoftwo(i) = powersoftwo(i-1) << 1
		}

		val numberofitems = new Array[Int](cBits)
		for (i <- 0 to cBits-1) {
			numberofitems(i) = 0
		}

		val values = new Array[HashSet[Int]](cBits)
		for (bit <- 0 to cBits-1) {
			values(bit) = new HashSet[Int]()
		}

		var nodeid = 0
		val str_lastidx = 2*(cBits-1)
		var no = 0
		for (node <- 0 to cNodes-1) {
			val l = lines.next()
			no = 0
			var nbits = 0
			var mask = 1
			for (i <- str_lastidx to 0 by -2) {
				if (l.charAt(i) == '1') {
					nbits = nbits + 1
					no |= mask
				}
				mask <<= 1
			}

			values(nbits).add(no)
			nodeid = nodeid + 1
		}

		new BitCluster(values)
	}
}