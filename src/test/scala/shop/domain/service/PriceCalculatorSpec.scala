package shop.domain.service

import org.scalatest.{MustMatchers, WordSpec}
import shop.domain.model.{Apple, Orange, ShoppingCart}
import shop.domain.service.discount.DiscountCalculator

/**
  * Created by rsekulski on 22.04.2017.
  */
class PriceCalculatorSpec extends WordSpec with MustMatchers {

  private val items = Seq(Apple, Apple, Orange, Apple)

  "PriceCalculator" should {

    "return total cost of all items in the shopping cart when no discount is provided" in {
      PriceCalculator.calculatePrice()(ShoppingCart(items)) mustEqual (Apple.price * 3  + Orange.price)
    }

    "return total cost of all items with applied discount" in {
      val discountCalculators = Seq(
        discountCalculatorWithDiscount(Apple.price),
        discountCalculatorWithDiscount(Apple.price * 2)
      )

      PriceCalculator.calculatePrice(discountCalculators)(ShoppingCart(items)) mustEqual
        (Apple.price * 3 + Orange.price - Apple.price - Apple.price * 2)
    }

    "return total cost of 0 when calculated discount is greater than total price" in {
      val discountCalculators = Seq(discountCalculatorWithDiscount(Apple.price * 10))

      PriceCalculator.calculatePrice(discountCalculators)(ShoppingCart(items)) mustEqual BigDecimal(0)
    }

    "return total cost of 0 if the shopping cart is empty" in {
      PriceCalculator.calculatePrice()(ShoppingCart()) mustEqual BigDecimal(0)
    }
  }

  private def discountCalculatorWithDiscount(discount: BigDecimal) = new DiscountCalculator {
    override def calculateDiscount(shoppingCart: ShoppingCart): BigDecimal = discount
  }
}
