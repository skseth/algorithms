package structs

import scala.collection.mutable._
import scala.annotation._

object heap {

	trait Heapable[T] {
		def itemAt(i:Int):T
		def setItem(i:Int, value:T):Unit
		def removeLastItemFromHeap():T
		def addItemAtEnd(value:T):Unit
		def itemCount():Int
		def compare(i:Int, j:Int):Int

		def hasItems = itemCount() > 0

		@tailrec
		final def heapify(idx:Int): Unit = {
			val smallest = topOfThree(idx, left(idx), right(idx))

			if (smallest != idx) {
			  swap(idx, smallest)
			  heapify(smallest)
			}
		}

		@tailrec
		final def bubble_up(idx:Int):Unit = {
			if (idx > 0) {
				val par = parent(idx)
			    val smallest = topOfTwo(idx, par)

			    if (smallest != par) {
			      swap(par, idx)
			      bubble_up(par)
			    }			
			}
		}

		def swap(i:Int, j:Int) = {
			val t = itemAt(i)
			setItem(i, itemAt(j))
			setItem(j, t)
		}

		def extractTop() = {
			swap(0,itemCount() - 1)
			val top = removeLastItemFromHeap()
			heapify(0)
			top
		}

		def insert(item:T) = {
			addItemAtEnd(item)
			bubble_up(itemCount()-1)
		}

		def heap_build = {
			for (i <- parent(itemCount()-1) to 0 by -1) heapify(i)
		}

		private def topOfThree(idx1:Int, idx2:Int, idx3:Int) = {
		    val largest = if (idx2 < itemCount() && compare(idx2,idx1) > 0) idx2 else idx1
		    if (idx3 < itemCount() && compare(idx3, largest) > 0) idx3 else largest
		}

		private def topOfTwo(idx1:Int, idx2:Int) = {
		    if (idx2 < itemCount() && compare(idx2,idx1) > 0) idx2 else idx1
		}

		private def parent(idx:Int):Int = (idx-1)/2
		private def left(idx:Int):Int = 2*idx+1
		private def right(idx:Int):Int = 2*idx+2
	}

	class ArrayBufferHeap[T](val a:ArrayBuffer[T])(implicit ord:Ordering[T]) extends Heapable[T] {
		def itemAt(i:Int) = a(i)
		def setItem(i:Int, value:T) = a(i) = value
		def removeLastItemFromHeap() = a.remove(a.length-1)
		def itemCount() = a.length
		def compare(i:Int, j:Int) = ord.compare(a(i), a(j))
		def addItemAtEnd(value:T) = a += value
		def itemAtTop() = a(0)
	}
}
