package structs

import scala.collection.mutable._
import scala.util.Random


case class Node[T](id:T) {
	val edges = ArrayBuffer[T]()

	def addEdge(e:T) = {
		edges += e
	}

	def removeEdge(del_e:T) {
		edges --= edges.filter(e => e == del_e)
	}

	def mergeEdge(to:T, from:T) {
		for (i <- 0 to edges.length-1) {
			if (edges(i) == from) {
				edges(i) = to
			}
		}
	}

	def mergeWith(from:Node[T]) = {
		this.removeEdge(from.id)
		from.edges.map(e => if (e != this.id) this.addEdge(e))
	}

	def allEdges = this.edges

	def randomEdge() = {
		val random_index = Random.nextInt(edges.size);
		(id, edges(random_index))
	}

	override def clone() = {
		val newnode = Node[T](id)
		newnode.edges ++= edges
		newnode
	}

	def print() {
		println("Node ", id, edges.toList)
	}
}

class Graph[T:Ordering]() {
	val nodes = new HashMap[T, Node[T]]()
	val nodekeys = new ArrayBuffer[T]()

	def addNode(n:Node[T]) = {
		nodes(n.id) = n
		nodekeys += n.id
	}

	def removeNode(del_n:T):Unit = {
		nodes.remove(del_n)
		nodekeys -= del_n
	}

	def join(to:T, from:T) = { 
		nodes(to).mergeWith(nodes(from)); 
		nodes.values.foreach(n => {
			n.mergeEdge(to,from)
		})
		removeNode(from)
	}

	def randomNode() = {
		val random_index = Random.nextInt(nodekeys.size);
		nodes(nodekeys(random_index))
	}

	def randomEdge() = {
		randomNode().randomEdge
	}

	override def clone() = {
		val g = new Graph[T]()
		nodes.values.map(n => g.addNode(n.clone()))
		g
	}

	def allNodes() = this.nodes

	def length() = nodes.size

	def print() = {
		nodekeys.sorted.map(k => nodes(k).print())
	}
}

object Graph {

	def fromFile(filename:String) = {
		var graph = new Graph[Int]()

		scala.io.Source.fromFile(filename).getLines().foreach{
		    x => 
			    val a = x.split("\\s+")
			    val node = Node[Int](a(0).toInt)
			    var s = a.view.slice(1, a.length).map(y => node.addEdge(y.toInt)).force
			    graph.addNode(node)
		}
		graph
	}
}