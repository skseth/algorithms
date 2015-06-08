package graphs
import structs._
import scala.collection.mutable._

class Kruskals {

	case class Edge(node1:Int, node2:Int, length:Int)

	val edges = new PriorityQueue[Edge]()(Ordering.by(e => -e.length))
	val nodes = Partition[Int]()
	var tree = List[Edge]()
	var mstcost = 0
	var mindistance = 0
	var minedge:Edge = null

	def addNode(n:Int) = {
		nodes.add(n)
	}

	def addEdge(n1:Int, n2:Int, length:Int) = {
		edges.enqueue(Edge(n1, n2, length))
	}

	def readEdgeFile(filename:String) = {
		var lines = scala.io.Source.fromFile(filename).getLines()

		var cNodes = lines.next().toInt

		for (i <- 1 to cNodes) {
			addNode(i)
		}

		for (l <- lines) {
			val a = l.split(" ")
			addEdge(a(0).toInt, a(1).toInt, a(2).toInt)
		}
	}

	def computeMST(k:Int = 1) = {

		var edgecount = Array[Int](0,0,0)
		while(nodes.size > k && !edges.isEmpty) {
			var edge = edges.dequeue()
			if(edge.length < 3) {
				edgecount(edge.length) += 1
			}

			if (nodes.union(edge.node1, edge.node2)) {
				tree = edge::tree
				mstcost = mstcost + edge.length
			}
		}

		while (!edges.isEmpty && mindistance == 0) {
			var edge = edges.dequeue()
			if (!nodes.hasSameRoot(edge.node1, edge.node2)) {
				minedge = edge
				mindistance = minedge.length
			}
		}

	    mstcost
	}
}