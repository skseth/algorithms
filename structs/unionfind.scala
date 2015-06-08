package structs
import scala.annotation.tailrec
import scala.collection.mutable._


case class PNode[T](val elem : T)(var rank : Int, var parent : Option[PNode[T]]) 

class Partition[T](initialElements : scala.collection.immutable.Seq[T]) {
  var cPartitions = 0

  def add(e1 : T) : Unit = {
    nodes += (e1 -> PNode(e1)(0, None))
    cPartitions += 1
  }

  def exists(e1:T) = nodes.contains(e1)

  def find(e1 : PNode[T]) = {
    @tailrec
    def findacc(e1 : PNode[T], path: List[PNode[T]]):PNode[T] = e1.parent match {
      case None => {
        for (node <- path) {
          node.parent = Some(e1)
        }
        e1        
      }

      case Some(node_parent) => {
        findacc(node_parent, e1::path)
      }
    }

    findacc(e1, Nil)
  }

  def hasSameRoot(e1 : T, e2 : T):Boolean = {
    return find(nodes(e1)) == find(nodes(e2))    
  }

  def union(e1 : T, e2 : T):Boolean = {

    val roote1 = find(nodes(e1))
    val roote2 = find(nodes(e2))

    if (roote1.elem != roote2.elem) {
      if (roote1.rank > roote2.rank) {
        roote2.parent = Some(roote1)
        roote1.rank += 1
      }
      else if (roote1.rank < roote2.rank) {
        roote1.parent = Some(roote2)
        roote2.rank += 1
      }
      else {
        roote1.parent = Some(roote2)        
      }
      cPartitions -= 1
      return true
    }
    else {
      return false
    }
  }

  def size : Int = {
    cPartitions
  }

  def print() = {
    val clusters = new HashMap[T,List[T]]()
    for ((elem,node) <- nodes) {
      val parelem = find(node).elem
      if (!clusters.contains(parelem)) {
        clusters(parelem) = Nil
      }
      clusters(parelem) = elem::clusters(parelem)
    }

    println("---clusters")
    for ((k,v) <- clusters) {
      println(v)
    }
    println("---clusters done")
  }

  private val nodes = new HashMap[T,PNode[T]]()

  initialElements map (add _) 
}

object Partition {

  def apply[T](initialElements : scala.collection.immutable.Seq[T] = Nil) = new Partition[T](initialElements)

}

