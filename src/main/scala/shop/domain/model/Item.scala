package shop.domain.model

/**
  * Created by rsekulski on 22.04.2017.
  */
sealed trait Item {
  def price: BigDecimal
}

case object Apple extends Item {
  val price = BigDecimal(0.60)
  val name = "Apple"
}

case object Orange extends Item {
  val price = BigDecimal(0.25)
  val name = "Orange"
}