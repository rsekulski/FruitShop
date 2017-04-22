package shop.domain.service.parser

import org.scalatest.{MustMatchers, WordSpec}
import shop.domain.model.{Apple, Orange}

import scalaz.Success

/**
  * Created by rsekulski on 22.04.2017.
  */
class ItemStringParserSpec extends WordSpec with MustMatchers {

  "ItemStringParser" should {
    "return Apple when provided Apple name" in {
      ItemStringParser.parseItem(Apple.name) mustEqual Success(Apple)
    }

    "return Orange when provided Orange name" in {
      ItemStringParser.parseItem(Orange.name) mustEqual Success(Orange)
    }

    "return Failure when provided unknown name" in {
      ItemStringParser.parseItem("NAME").isFailure mustBe true
    }
  }
}
