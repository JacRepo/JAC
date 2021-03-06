package auction

import com.ascoop.Future.done
import auction.DataTypes._
import auction.Functions._
import com.ascoop.TypedActor

class Bidder[T](arrival: Double, deadline: Double, destination: Option[Destination], budget: Price, risk: Double, info: T)
    extends TypedActor {

  private var finished = false

  def announce(caller: Auctioneer[T], slot: Route, price: Price) = messageHandler {
    if (! finished) {
      val timeFit = fit(arrival, deadline, destination, slot)
      val myOffer = bidStrategy(price, budget, timeFit, risk)
      if (myOffer < 0) {
        finished = true
        done
      } else {
        caller.bid(this, slot, myOffer, info) // in this case, we are only done when the call is complete
      }
    } else {
      done
    }
  }

  def sold(slot: Route) = messageHandler {
    println(s"I won $slot")
    finished = true
    done(info)
  }

}
