package shop.domain.service.discount

import shop.domain.model.{Apple, ShoppingCart}

/**
  * Created by rsekulski on 22.04.2017.
  */
object TwoApplesForOne extends DiscountCalculator {

  override def calculateDiscount(shoppingCart: ShoppingCart): BigDecimal = {
    val numberOfApples = shoppingCart.items.count{
      case Apple => true
      case _ => false
    }

    Apple.price * calculateNumberOfFreeApples(numberOfApples)
  }

  private def calculateNumberOfFreeApples(numberOfApples: Int) =
    numberOfApples / 2
}
