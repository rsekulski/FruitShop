package shop.domain.service.parser

import shop.domain.model.ShoppingCart

import scalaz.Scalaz._
import scalaz.{Success, ValidationNel}

/**
  * Created by rsekulski on 22.04.2017.
  */
trait ShoppingCartStringParser {
 this: ItemStringParser =>

  val separator = ';'

  def parseShoppingCart(inputString: String): ValidationNel[String,ShoppingCart] = {
    if (inputString.isEmpty) Success(ShoppingCart())
    else inputString.split(separator).toList.traverseU(name => parseItem(name).toValidationNel).map(ShoppingCart)
  }
}

object ShoppingCartStringParser extends ShoppingCartStringParser with ItemStringParser
