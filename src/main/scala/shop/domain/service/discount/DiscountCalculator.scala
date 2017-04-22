package shop.domain.service.discount

import shop.domain.model.ShoppingCart

/**
  * Created by rsekulski on 22.04.2017.
  */
trait DiscountCalculator {

  def calculateDiscount(shoppingCart: ShoppingCart): BigDecimal
}
