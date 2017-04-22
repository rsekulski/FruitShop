package shop.domain.service.parser

import shop.domain.model.{Apple, Item, Orange}

import scalaz.{Failure, Success, Validation}

/**
  * Created by rsekulski on 22.04.2017.
  */
trait ItemStringParser {

  def parseItem(name: String): Validation[String, Item] = name match {
    case Apple.name => Success(Apple)
    case Orange.name => Success(Orange)
    case _ => Failure(s"Invalid name: $name")
  }

}

object ItemStringParser extends ItemStringParser
