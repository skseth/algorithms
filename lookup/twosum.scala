package lookup

import scala.collection.mutable._
import scala.math._

object twosum {

	def find_distinct_2sum(filename:String, rangestart:Long, rangeend:Long) = {
		val dist = new HashSet[Long]()
		val nos = new TreeSet[Long]()

		def readnumbers() = scala.io.Source.fromFile(filename).getLines().foreach(x => nos.add(x.toLong))

		def addSum(no:Long, otherno:Long) = {
			dist.add(no + otherno)
		}

		def processNumber(no:Long) = {
			var low:Long = rangestart - no
			if (low < no+1) low = no + 1

			val high = rangeend - no

			nos.iteratorFrom(low).takeWhile(otherno => otherno <= high).foreach(otherno => addSum(no, otherno))
		}

		def processnumbers() = nos.iterator.takeWhile(no => no < rangeend).foreach(no => processNumber(no))

		readnumbers()

		println(nos.size)

		processnumbers()

		dist.size

	}
}

object twosum2 {

	class MedianHash(rangestart:Long, rangeend:Long) {
		val w = rangeend - rangestart
		val emptyTreeSet = TreeSet[Long]()
		val hashEntries = new HashMap[Long, TreeSet[Long]]()

		def hashIterator = hashEntries.keysIterator

		def hash(x:Long) = (x / w)

		def add(x:Long) = getEditableHashEntry(hash(x)).add(x)

		private def getEditableHashEntry(entryIdx:Long) = hashEntries.getOrElseUpdate(entryIdx, new TreeSet[Long]())

		def getHashEntry(entryIdx:Long) = hashEntries.getOrElse(entryIdx, emptyTreeSet)
	}

	def find_distinct_2sum(filename:String, rangestart:Long, rangeend:Long) = {
		val dist = new HashSet[Long]()
		val nos = new MedianHash(rangestart, rangeend)

		def readnumbers() = scala.io.Source.fromFile(filename).getLines().foreach(x => nos.add(x.toLong))

		def addSum(no:Long, otherno:Long) = {
			if (no != otherno) dist.add(no + otherno)
		}

		def processNumberCandidates(no:Long, hash:Long) = {
			val che = nos.getHashEntry(hash)
			var low = rangestart - no
			val high = rangeend - no

			che.iteratorFrom(low).takeWhile(otherno => otherno <= high).foreach(otherno => addSum(no, otherno))
		}

		def processNumber(no:Long) = {
			val hash1 = nos.hash(rangestart - no)
			val hash2 = hash1 + 1
			processNumberCandidates(no, hash1)
			processNumberCandidates(no, hash2)
		}

		readnumbers()

		for (i <- nos.hashIterator) {
			val he = nos.getHashEntry(i)

			he.iterator.foreach(no => processNumber(no))
		}


		dist.size

	}
}