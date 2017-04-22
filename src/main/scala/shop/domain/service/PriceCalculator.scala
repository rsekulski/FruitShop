package shop.domain.service

import shop.domain.model.ShoppingCart

/**
  * Created by rsekulski on 22.04.2017.
  */
trait PriceCalculator {

  def calculatePrice(shoppingCart: ShoppingCart): BigDecimal =
    shoppingCart.items.foldLeft(BigDecimal(0))((sum, item) => sum + item.price)
}

object PriceCalculator extends PriceCalculator
