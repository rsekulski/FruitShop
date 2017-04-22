package shop.domain.service.discount

import org.scalatest.{MustMatchers, WordSpec}
import shop.domain.model.{Apple, Orange, ShoppingCart}

/**
  * Created by rsekulski on 22.04.2017.
  */
class TwoApplesForOneSpec extends WordSpec with MustMatchers {

  "TwoApplesForOne" should {
    "calculate discount on apples that" should {
      "be equal to price of one apple for every two apples" in {
        TwoApplesForOne.calculateDiscount(ShoppingCart()) mustEqual BigDecimal(0)
        TwoApplesForOne.calculateDiscount(ShoppingCart(Seq(Apple))) mustEqual BigDecimal(0)
        TwoApplesForOne.calculateDiscount(ShoppingCart(Seq(Apple, Apple))) mustEqual Apple.price
        TwoApplesForOne.calculateDiscount(ShoppingCart(Seq(Apple, Apple, Apple))) mustEqual Apple.price
        TwoApplesForOne.calculateDiscount(ShoppingCart(Seq(Apple, Apple, Apple, Apple))) mustEqual Apple.price * 2
      }

      "ignore other items" in {
        val shoppingCart = ShoppingCart(Seq(Apple, Orange, Apple, Orange))

        TwoApplesForOne.calculateDiscount(shoppingCart) mustEqual Apple.price
      }
    }
  }
}
