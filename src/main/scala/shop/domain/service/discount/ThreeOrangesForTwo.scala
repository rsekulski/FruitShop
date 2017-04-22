package shop.domain.service.discount

import shop.domain.model.{Orange, ShoppingCart}

/**
  * Created by rsekulski on 22.04.2017.
  */
object ThreeOrangesForTwo extends DiscountCalculator {

  override def calculateDiscount(shoppingCart: ShoppingCart): BigDecimal = {

    val numberOfOranges = shoppingCart.items.count {
      case Orange => true
      case _ => false
    }

    Orange.price * calculateNumberOfFreeOranges(numberOfOranges)
  }

  private def calculateNumberOfFreeOranges(numberOfOranges: Int) =
    numberOfOranges / 3
}
