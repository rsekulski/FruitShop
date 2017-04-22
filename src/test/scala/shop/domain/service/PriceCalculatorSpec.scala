package shop.domain.service

import org.scalatest.{MustMatchers, WordSpec}
import shop.domain.model.{Apple, Orange, ShoppingCart}

/**
  * Created by rsekulski on 22.04.2017.
  */
class PriceCalculatorSpec extends WordSpec with MustMatchers {

  "PriceCalculator" should {
    "return total cost of all items in the shopping cart" in {
      val items = Seq(Apple, Apple, Orange, Apple)

      PriceCalculator.calculatePrice(ShoppingCart(items)) mustEqual (Apple.price * 3  + Orange.price)
    }

    "return total cost of 0 if the shopping cart is empty" in {
      PriceCalculator.calculatePrice(ShoppingCart()) mustEqual BigDecimal(0)
    }
  }
}
