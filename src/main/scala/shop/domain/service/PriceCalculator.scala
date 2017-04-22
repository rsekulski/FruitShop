package shop.domain.service

import shop.domain.model.ShoppingCart
import shop.domain.service.discount.DiscountCalculator

/**
  * Created by rsekulski on 22.04.2017.
  */
trait PriceCalculator {

  def calculatePrice(discountsList: Seq[DiscountCalculator] = Seq.empty)(shoppingCart: ShoppingCart): BigDecimal = {
    val price = shoppingCart.items.foldLeft(BigDecimal(0))((sum, item) => sum + item.price)
    val discount = discountsList.foldLeft(BigDecimal(0))((discount, calculator) => discount + calculator.calculateDiscount(shoppingCart))

    (price - discount).max(BigDecimal(0))
  }
}

object PriceCalculator extends PriceCalculator
