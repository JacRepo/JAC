package pingpong


object MainPingPong {

  def main(args: Array[String]): Unit = {
    val N = 40000
    val pong = new PongActor
    val ping = new PingActor(N,pong)
    ping!ping.start
  }

}