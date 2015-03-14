package structs

import scala.collection.mutable._
import scala.util.Random


case class Node[T](id:T) {
	val edges = ArrayBuffer[T]()
	val edgelength = ArrayBuffer[Int]()
	val revedges = ArrayBuffer[T]()

	def addEdge(e:T, l:Int = 1) = {
		edges += e
		edgelength += l
	}

	def addReverseEdge(e:T) = {
		revedges += e
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

	// todo - take care of reverse edges
	def mergeWith(from:Node[T]) = {
		this.removeEdge(from.id)
		from.edges.map(e => if (e != this.id) this.addEdge(e))
	}

	def allEdges = this.edges
	def allRevEdges = this.revedges

	def edgeIterator = for (i <- 0 to edges.length-1) yield (edges(i), edgelength(i))

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
		println("Node ", id, edges.toList, edgelength.toList, revedges.toList)
	}
}

class Graph[T:Ordering] (val isUndirected:Boolean=false) {
	val nodes = new HashMap[T, Node[T]]()
	val nodekeys = new ArrayBuffer[T]()

	def addEdge(from:T, to:T, l:Int = 1) = {
		if (!nodes.isDefinedAt(from)) addNode(from)
		if (!nodes.isDefinedAt(to)) addNode(to)

		nodes(from).addEdge(to, l)

		if (isUndirected) {
			nodes(to).addEdge(from, l)
		}
		else {
			nodes(to).addReverseEdge(from)			
		}
	}

	def addNode(nodeid:T) = {
		if (!nodes.isDefinedAt(nodeid)) {
			nodes(nodeid) = new Node[T](nodeid)
			nodekeys += nodeid
		}
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

	def getNode(id:T) = nodes(id)

	def randomNode() = {
		val random_index = Random.nextInt(nodekeys.size);
		nodes(nodekeys(random_index))
	}

	def randomEdge() = {
		randomNode().randomEdge
	}

	override def clone() = {
		val g = new Graph[T](isUndirected)
		nodes.values.foreach(n => {
			g.addNode(n.id)
			for (e <- n.edgeIterator) g.addEdge(n.id, e._1, e._2)
		})
		g
	}

	def allNodes() = this.nodes

	def length() = nodes.size

	def print() = {
		nodekeys.sorted.map(k => nodes(k).print())
	}
}

object Graph {

	def fromEdgeFile(filename:String) = {
		var graph = new Graph[Int]()

		scala.io.Source.fromFile(filename).getLines().foreach{
		    x => 
			    val a = x.split("\\s+")
			    graph.addEdge(a(0).toInt, a(1).toInt)
		}
		graph
	}

	def parseAndAddEdge(graph:Graph[Int], srcnodeid:Int, edgestr:String) = {
		val a = edgestr.split(",")
		val edgenode = a(0).toInt
		val edgelength = if (a.length > 1) a(1).toInt else 1
		(edgenode, edgelength)
		graph.addEdge(srcnodeid, edgenode, edgelength)
	}

	def parseAndAddNode(graph:Graph[Int], line:String) = {
	    val a = line.split("\\s+")
	    val nodeid = a(0).toInt
	    graph.addNode(nodeid)
	    var s = a.view.slice(1, a.length).map(y => {
	    	parseAndAddEdge(graph, nodeid, y)
	    }).force
	}

	def fromFile(filename:String, isUndirected:Boolean = false) = {
		var graph = new Graph[Int](isUndirected)

		scala.io.Source.fromFile(filename).getLines().foreach{
		    x => parseAndAddNode(graph, x)
		}
		graph
	}
}