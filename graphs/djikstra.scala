package graphs

import scala.collection.mutable._
import scala.annotation._
import structs._

object djikstra {
	case class NodeValue(nodeid:Int)(var idx:Int = -1, var estimate:Int = Int.MaxValue, var pred:Int = -1) 

	class DjikstraHeap(cNodes:Int) extends heap.Heapable[NodeValue] {

		var cHeapNodes = 0
		val nodes = new HashMap[Int, NodeValue]()
		val nodeArray = new Array[NodeValue](cNodes)

		def addItemAtEnd(value:NodeValue) = {
			value.idx = cHeapNodes
			nodeArray(cHeapNodes) = value
			nodes(value.nodeid) = nodeArray(cHeapNodes)
			cHeapNodes += 1
		}

		def itemAt(i:Int) = nodeArray(i)

		def setItem(i:Int, value:NodeValue) = {
			nodeArray(i) = value
			value.idx = i
		}

		def itemCount() = cHeapNodes

		def removeLastItemFromHeap() = {
			val retval = nodeArray(cHeapNodes-1)
			cHeapNodes = cHeapNodes - 1
			retval
		}

		def compare(i:Int, j:Int) = nodeArray(j).estimate - nodeArray(i).estimate

		def getNodes() = nodes

		def getNode(nodeid:Int) = nodes(nodeid)

		def improveEstimate(nodeid:Int, estimate:Int, pred:Int) = {
			val nodevalue = nodes(nodeid)
			nodevalue.estimate = estimate
			nodevalue.pred = pred
			bubble_up(nodevalue.idx)
		}

		def print = for (n <- nodeArray) printf("(%d,%d)\n", n.nodeid, n.estimate)
	}

	def shortest_path(g:Graph[Int], start:Int) = {
		val heap = new DjikstraHeap(g.length)

		g.allNodes.values.iterator.foreach(n => heap.addItemAtEnd(NodeValue(n.id)()))
		heap.improveEstimate(start, 0, start)

		while (heap.hasItems) {
			val topnode = heap.extractTop()

			for ((edgeNodeId, edgeNodeLength) <- g.getNode(topnode.nodeid).edgeIterator) {
				val enode = heap.getNode(edgeNodeId)
				if (enode.estimate > topnode.estimate + edgeNodeLength) {
					heap.improveEstimate(edgeNodeId, topnode.estimate + edgeNodeLength, topnode.nodeid)
				}
			}
		}

		heap.getNodes
	}
}