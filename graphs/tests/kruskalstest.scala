import org.scalatest.FeatureSpec
import org.scalatest.GivenWhenThen
import structs._
import graphs._
import scala.collection.mutable._

 
class KruskalsSpec extends FeatureSpec with GivenWhenThen {

  def runKruskals(filename:String, expectedCost:Long) = {
        given("a file with edge data")
        var kruskal = new Kruskals()
        kruskal.readEdgeFile(filename)

        when("mst is computed using kruskal's algorithm")
        val cost = kruskal.computeMST(1)

        then("mst is calculated correctly")
        assert(cost == expectedCost)
  }

  def runClustering(filename:String, noofpartitions:Int, expectedMinDistance:Int) = {
        given("a file with edge data")
        var kruskal = new Kruskals()
        kruskal.readEdgeFile(filename)

        when("clustering is done using kruskal's algorithm")
        val cost = kruskal.computeMST(noofpartitions)

        then("min distance is calculated correctly")
        assert(kruskal.mindistance == expectedMinDistance)
  }


  def runBitClustering(filename:String, expectedNoofPartitions:Int) = {
        given("a file with bit cluster data")
        var bc = BitCluster.fromBitFile(filename)

        when("clustering is done using kruskal's algorithm")
        val noofpartitions = bc.partition()

        then("no of partitions are calculated correctly")
        assert(noofpartitions == expectedNoofPartitions)

  }

  feature("calculate mindistance k-cluster") {

    scenario("kruskals - small file 1a") {
        runClustering("data/clustering_small1.txt", 2, 6)
    }

    scenario("kruskals - small file 1b") {
        runClustering("data/clustering_small1.txt", 3, 5)
    }

    scenario("kruskals - small file 1c") {
        runClustering("data/clustering_small1.txt", 4, 2)
    }


    scenario("kruskals - small file 2") {
        runClustering("data/clustering_small2.txt", 4, 10)
    }


    scenario("kruskals - 2d-a") {
        runClustering("data/clustering_2d.txt", 2, 4472)
    }

    scenario("kruskals - 2d-b") {
        runClustering("data/clustering_2d.txt", 3, 3606)
    }

    scenario("kruskals - 2d-c") {
        runClustering("data/clustering_2d.txt", 4, 1414)
    }


    scenario("kruskals - clustering1") {
        runClustering("data/clustering1.txt", 4, 106)
    }
  }

  feature("calculate bit cluster partition") {

    scenario("bit cluster - tiny") {
        runBitClustering("data/bitclustering_singlebitsize.txt", 1)
    }

    scenario("bit cluster - 1000") {
        runBitClustering("data/bitclustering_1000.txt", 989)
    }

    scenario("bit cluster - 10000") {
        runBitClustering("data/bitclustering_10000.txt", 9116)
    }

    scenario("bit cluster - big") {
        runBitClustering("data/bitclustering_big.txt", 6118)
    }

  }
}