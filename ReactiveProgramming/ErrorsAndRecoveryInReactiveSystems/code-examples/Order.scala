import scala.util.Try

case class SKU(id: Long)
case class Money(value: BigDecimal)

case class Order(
  id:          Long,
  cost:        Money,
  items:       Seq[(Int,SKU)])

object Order {
  case class InvalidOrderString(str: String, reason: Throwable = null)
    extends RuntimeException(
      s"Invalid string $str. Expected tab-delimited format: id amount n1 sku1 n2 sku2 ...",
      reason)

  def parse(string: String): Try[Order] = Try {
    val array = string.split("\t")
    if (array.length % 2 != 0) throw InvalidOrderString(string)
    val id = array(0).toLong
    val cost = Money(BigDecimal(array(1)))
    val items = array.drop(2).sliding(2,2).map {
      case Array(n, sku) => (n.toInt, SKU(sku.toLong))
    }
    new Order(id, cost, items.toVector)
  }
}
