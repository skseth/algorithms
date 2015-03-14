package graphs

import scala.math._
import scala.collection.mutable._
import structs._

object minbottleneck {

	def find[T](graph:Graph[T], start:T, dest:T) = {
		val visited = new HashMap[T, Boolean]
		val best_path_to_dest = new HashMap[T, List[T]]
		val min_bottleneck_to_dest = new HashMap[T, Int]

		def dfs_loop(n:T):Unit = {
			visited(n) = true

			min_bottleneck_to_dest(n) = Int.MaxValue
			best_path_to_dest(n) = Nil

			for ((w,length) <- graph.getNode(n).edgeIterator) {
				if (length < min_bottleneck_to_dest(n)) {
					if (!visited.contains(w)) dfs_loop(w)

					if (min_bottleneck_to_dest(w) < min_bottleneck_to_dest(n)) {
						min_bottleneck_to_dest(n) = max(min_bottleneck_to_dest(w), length)
						best_path_to_dest(n) = n :: best_path_to_dest(w)
					}
				}
			}
		}

		// initialize destination
		visited(dest) = true
		best_path_to_dest(dest) = List(dest)
		min_bottleneck_to_dest(dest) = 0

		dfs_loop(start)

		(min_bottleneck_to_dest(start), best_path_to_dest(start))			
	}
}