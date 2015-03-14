package lookup

import scala.collection.mutable._
import structs._

object medianmaint {

	def calculate(filename:String) = {

		val lows = new heap.ArrayBufferHeap(new ArrayBuffer[Int]())
		val highs = new heap.ArrayBufferHeap(new ArrayBuffer[Int]())(implicitly[Ordering[Int]].reverse)
		val medians = new ArrayBuffer[Int]()

		def addItem(i:Int) = {
			if (lows.itemCount() == 0 || i < lows.itemAtTop()) {
				lows.insert(i)
				if (lows.itemCount() - highs.itemCount() > 1) {
					highs.insert(lows.extractTop())
				}
			}
			else {
				highs.insert(i)
				if (highs.itemCount() - lows.itemCount() > 0) {
					lows.insert(highs.extractTop())
				}
			}
		}

		def processnumbers() = {
			scala.io.Source.fromFile(filename).getLines().foreach(x => {
				addItem(x.toInt)
				medians += lows.itemAtTop()
			})
		}

		def processmedians() = {
			val total = medians.foldLeft(0L)(_ + _)
			total % 10000
		}

		processnumbers

		println(medians.length)

		processmedians
	}
}
