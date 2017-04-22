package shop.domain.service.parser

import org.scalatest.{MustMatchers, WordSpec}
import shop.domain.model.{Apple, Orange, ShoppingCart}

import scalaz.{Failure, Success}

/**
  * Created by rsekulski on 22.04.2017.
  */
class ShoppingCartStringParserSpec extends WordSpec with MustMatchers {

  import ShoppingCartStringParser.separator

  "ShoppingCartStringParser" should {
    "return shopping cart from valid list of items" in {
      val input = s"${Apple.name}${separator}${Apple.name}${separator}${Orange.name}"

      ShoppingCartStringParser.parseShoppingCart(input) mustEqual Success(ShoppingCart(Seq(Apple, Apple, Orange)))
    }

    "should shopping cart for single item on input list" in {
      val input = "Apple"

      ShoppingCartStringParser.parseShoppingCart(input) mustEqual Success(ShoppingCart(Seq(Apple)))
    }

    "return exceptions list when cannot parse input string" in {
      val input = s"UnknownName;UnknownName2"

      ShoppingCartStringParser.parseShoppingCart(input) match {
        case Success(_) => fail(s"parsing input: $input should fail")
        case Failure(nel) => nel.size mustEqual 2
      }
    }

    "return empty shopping cart when provided input is empty" in {
      val input = ""

      ShoppingCartStringParser.parseShoppingCart(input) mustEqual Success(ShoppingCart())
    }

  }
}
