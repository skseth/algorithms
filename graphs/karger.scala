package graphs

import structs._

object karger {

	def mincut[T](g:Graph[T]) = {
		val g_copy = g.clone()

		while (g_copy.length > 2) {
			val (from,to) = g_copy.randomEdge
			g_copy.join(from,to)
		}
		g_copy.allNodes.values.toList(0).allEdges.length
	}

	def mincut_repeat[T](g:Graph[T]) = {
		var min_cut = g.length

		val nooftries = if (g.length < 100) 10*g.length*g.length else 2*g.length

		for ( j <- 0 to nooftries-1) {
			val newmincut = mincut[T](g)
			if (newmincut < min_cut) min_cut = newmincut
		}
		min_cut
	}
}