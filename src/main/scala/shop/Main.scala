package shop

import shop.domain.service.PriceCalculator
import shop.domain.service.parser.{ItemStringParser, ShoppingCartStringParser}

import scala.io.StdIn
import scalaz.{Failure, NonEmptyList, Success}

/**
  * Created by rsekulski on 22.04.2017.
  */
object Main extends App with ShoppingCartStringParser with ItemStringParser with PriceCalculator {

  val inputString = StdIn.readLine(s"Please provide items list in format: item1${separator}item2${separator}...${separator}itemN (e.g: Apple;Apple;Orange)\n")

  parseShoppingCart(inputString) match {
    case Success(shoppingCart) => (calculatePrice _ andThen printTotalPrice) (shoppingCart)
    case Failure(exceptions) => printExceptions(exceptions)
  }

  def printTotalPrice(totalPrice: BigDecimal) =
    println(s"Total price: $totalPrice")

  def printExceptions(exceptions: NonEmptyList[String]) =
    println(s"Exceptions: $exceptions")

}
