import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen

import scala.collection.mutable._
import scheduling._

class JobQueueSpec extends FeatureSpec with GivenWhenThen  {

  def runScenario(filename:String, ord:Ordering[Job], expectedWeight:BigInt) = {
	  given("jobs in a file")
	  val jq = JobQueue.fromFile(filename)

	  when("suboptimal scheduling with weight-length is done")
	  val weight = jq.scheduleJobs(ord)

	  then("the queue order is given")
	  assert(weight == expectedWeight)
  }

  feature("jobqueue - suboptimal") {
    scenario("small file") {
    	runScenario("data/smalljobs.txt", JobQueue.DiffOrdering, 61545)
    }
    scenario("big file") {
    	runScenario("data/jobs.txt", JobQueue.DiffOrdering, 69119377652L)
    }
  }


  feature("jobqueue - optimal") {
    scenario("small file") {
    	runScenario("data/smalljobs.txt", JobQueue.RatioOrdering, 60213)
    }
    scenario("big file") {
    	runScenario("data/jobs.txt", JobQueue.RatioOrdering, 67311454237L)
    }
  }

}

 