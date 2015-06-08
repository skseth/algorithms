package graphs

import structs._
import scala.collection.mutable._
import scala.annotation._

object bfs {

	case class NodeLayer[T](id:T, layer:Int)

	def walk[T:Ordering](startnode:T, graph:Graph[T]) = {

		val explored = SortedSet[T]()
		val nextqueue = Queue[NodeLayer[T]]()
		val g_copy = graph.clone()

		nextqueue += NodeLayer(startnode,0)
		explored += startnode

		while (nextqueue.length > 0) {
			val nodelayer = nextqueue.dequeue
			val node = graph.getNode(nodelayer.id)
			println(nodelayer.id, nodelayer.layer)
			node.allEdges.foreach(e => if (!explored.contains(e)) {
				nextqueue.enqueue(NodeLayer(e, nodelayer.layer + 1))
				explored += e
			})
		}
	}
}

object dfs {

	def walk[T:Ordering](startnode:T, graph:Graph[T]) = {
		val explored = SortedSet[T]()

		def walkAcc(node:structs.Node[T]):Unit = {
			println(node.id)
			explored += node.id
			node.allEdges.foreach(e => if (!explored.contains(e)) walkAcc(graph.getNode(e)))
		}

		walkAcc(graph.getNode(startnode))
	}

	// assumes DAG
	def toposort[T:Ordering](startnode:T, graph:Graph[T]) = {
		val explored = SortedSet[T]()
		val labels = new HashMap[T, Int]()
		var curlabel = graph.length

		def walkAcc(node:structs.Node[T]):Unit = {
			explored += node.id
			node.allEdges.foreach(e => if (!explored.contains(e)) walkAcc(graph.getNode(e)))
			labels(node.id) = curlabel
			curlabel -= 1
		}

		def loop() = {
			graph.allNodes.values.foreach(n => if (!explored.contains(n.id)) walkAcc(n) )
		}

		loop
		labels
	}

	def kosaraju[T:Ordering](graph:Graph[T]) = {
		var finalorder = List[T]()
		var sccs = List[List[T]]()


		def roundone() {
			val explored = SortedSet[T]()

			def dfsone(node:structs.Node[T]):Unit = {
				explored += node.id
				// tricky - a later sibling may have already have been explored due to DFS of earlier sibling
				node.allRevEdges.foreach(e => if (!explored.contains(e)) dfsone(graph.getNode(e)))
				finalorder = node.id::finalorder
			}

			// a reimplementation of dfsone with tail recursion
			@tailrec
			def dfsonestack(stack:Stack[List[T]]):Unit = stack.headOption match {
				case Some(Nil) => {
						stack.pop()
						dfsonestack(stack)
					}

				case Some(head::tail) if explored.contains(head) => {
							finalorder = head::finalorder
							stack.pop()
							dfsonestack(stack.push(tail.filter(e => !explored.contains(e)).toList))
						}
				case Some(head::tail) => {
							explored += head
							val children = graph.getNode(head).allRevEdges.filter(e => !explored.contains(e)).toList
							dfsonestack(stack.push(children))
						}
				case None => ()
			}

			graph.allNodes.values.foreach(n => {
				if (!explored.contains(n.id)) {
					val stack = Stack[List[T]]()
					stack.push(List(n.id))
					dfsonestack(stack)					
				}
			})
		}

		def roundtwo() {
			val explored = SortedSet[T]()
			var scc = List[T]()

			def dfstwostack(node:structs.Node[T]):Unit = {
				val stack = Stack[T]()
				val finalized = SortedSet[T]()

				stack.push(node.id)

				while (!stack.isEmpty) {
					val curnodeid = stack.pop()
					if (!finalized.contains(curnodeid)) {
						finalized += curnodeid
						explored += curnodeid
						scc = curnodeid::scc
					}
					stack.pushAll(graph.getNode(curnodeid).allEdges.filter(e => !explored.contains(e)))
				}
			}

			def dfstwo(node:structs.Node[T]):Unit = {
				explored += node.id
				scc = node.id::scc
				node.allEdges.foreach(e => if (!explored.contains(e)) dfstwo(graph.getNode(e)))
			}

			finalorder.foreach(n => {
				if (!explored.contains(n)) {
					dfstwostack(graph.getNode(n))
					sccs = scc :: sccs
					scc = List[T]()
				}
			})
		}

		roundone()
		println("roundone over")
		roundtwo()

		sccs.sortBy(- _.size)
	}

}


