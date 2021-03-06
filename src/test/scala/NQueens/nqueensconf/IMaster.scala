package NQueens.nqueensconf
import com.ascoop.{Actor, Future}

trait IMaster extends Actor with Ordered[Actor] {
  def sendWork( list : Array[Int],  depth : Int,  priorities : Int): Future[Void]
  def success(solution: Array[Int]): Future[Void]
}
