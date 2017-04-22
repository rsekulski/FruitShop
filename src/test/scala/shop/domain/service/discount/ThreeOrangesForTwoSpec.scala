package shop.domain.service.discount

import org.scalatest.{MustMatchers, WordSpec}
import shop.domain.model.{Apple, Orange, ShoppingCart}

/**
  * Created by rsekulski on 22.04.2017.
  */
class ThreeOrangesForTwoSpec extends WordSpec with MustMatchers {

  "ThreeOrangesForTwo" should {
    "calculate discount on oranges that" should {
      "be equal to price of one orange for every three oranges" in {
        val shoppingCart = ShoppingCart(Seq(Orange, Orange, Orange, Orange))

        ThreeOrangesForTwo.calculateDiscount(ShoppingCart()) mustEqual BigDecimal(0)
        ThreeOrangesForTwo.calculateDiscount(ShoppingCart(Seq(Orange))) mustEqual BigDecimal(0)
        ThreeOrangesForTwo.calculateDiscount(ShoppingCart(Seq(Orange,Orange))) mustEqual BigDecimal(0)
        ThreeOrangesForTwo.calculateDiscount(ShoppingCart(Seq(Orange,Orange,Orange))) mustEqual Orange.price
        ThreeOrangesForTwo.calculateDiscount(ShoppingCart(Seq(Orange,Orange,Orange,Orange))) mustEqual Orange.price
        ThreeOrangesForTwo.calculateDiscount(ShoppingCart(Seq(Orange,Orange,Orange,Orange,Orange))) mustEqual Orange.price
        ThreeOrangesForTwo.calculateDiscount(ShoppingCart(Seq(Orange,Orange,Orange,Orange,Orange,Orange))) mustEqual Orange.price * 2
      }

      "ignore other items" in {
        val shoppingCart = ShoppingCart(Seq(Apple, Orange, Apple, Orange, Orange))

        ThreeOrangesForTwo.calculateDiscount(shoppingCart) mustEqual Orange.price
      }
    }
  }
}
