// Futures as Combinators
// New to Scala 2.10 (SIP-14)

import scala.concurrent.{future, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

def print_time(msg: String) {
  printf("%d: %s\n", System.currentTimeMillis, msg)
}

case class NoInventory(sku: Long) extends RuntimeException

def inventoryFor(sku: Long): (Long,Int) = 
  if (sku%2 == 0)  (sku,10) else throw NoInventory(sku)

// Hack: uses a mutable list and synchronizes updates.
case class ShoppingCart(customerName: String) {
  var items: List[(Long, Int)] = Nil
  def addItem(sku: Long, count: Int): Unit = count match {
    case 0 => 
    case n => synchronized { items ::= (sku, n) }
  }
  override def toString = s"ShoppingCart(customerName = $customerName, items = $items"
}

val inventories = for {
  sku <- 1 to 5
} yield (future { inventoryFor(sku) })

var cart = new ShoppingCart("John Doe")
inventories.foreach { fut =>
  fut map {  // could also do fut onSuccess
    case (sku, 0) => 
    case (sku, inventory) => cart.addItem(sku, 1)
  } recover {  // could also do fut onFailure. Note that's different than the chaining of "recover" here.
    case NoInventory(sku) => println("No inventory for SKU: "+sku)
  }
}

print_time(cart.toString)
