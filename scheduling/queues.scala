package scheduling
import structs.heap._
import scala.collection.mutable._

case class Job(id:Int, weight:Int, length:Int)

class JobQueue {
	val jobs = new ArrayBuffer[Job]

	def jobsIterator = jobs.iterator

	def addJob(id:Int, weight:Int, length:Int) = {
		jobs += Job(id, weight, length)
	}


	def scheduleJobs(ord:Ordering[Job]) = {
		val heap = new ArrayBufferHeap[Job](jobs)(ord)
		heap.heap_build
		var starttime = 0L
		var totalweight:BigInt = 0L
		while (heap.hasItems) {
			val job = heap.extractTop()
			starttime += job.length
			totalweight += job.weight*starttime
		}
		totalweight
	}
}


object JobQueue {

	def fromFile(filename:String) = {
		val jq = new JobQueue

		val lines = scala.io.Source.fromFile(filename).getLines()

		val length = lines.next().toInt

		for (i <- 1 to length) {
			val jdata = lines.next().split(" ")
			jq.addJob(i, jdata(0).toInt, jdata(1).toInt)
		}

		jq
	}

	object DiffOrdering extends Ordering[Job] {
		def diffmetric(job:Job) = job.weight - job.length
		def compare(job1:Job, job2:Job):Int = {
			val job1metric = diffmetric(job1)
			val job2metric = diffmetric(job2)

			if (job1metric == job2metric) {
				job1.weight compare job2.weight
			}
			else {
				job1metric compare job2metric
			}			
		}
	}

	object RatioOrdering extends Ordering[Job] {
		def ratiometric(job:Job) = job.weight.toDouble / job.length.toDouble
		def compare(job1:Job, job2:Job):Int = ratiometric(job1) compare ratiometric(job2)
	}
}