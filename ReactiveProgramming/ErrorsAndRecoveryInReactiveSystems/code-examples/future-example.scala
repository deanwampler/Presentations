// Example using Future.
// First, compile Order.scala in this directory:
//   scalac Order.scala
// Then run this script:
//   scala future-example.scala

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

case class Account(id: Long, orderIds: Seq[Long])

// Fake a remote service, such as a database, for retrieving an account.
val accountDB = Map(
  1L -> Account(1, Seq(1, 4, 5)),
  2L -> Account(2, Seq(2, 3)))

// Fake a remote service, such as a database, for retrieving an order.
val orderDB = Map(
  1L -> Order(1, Money(100.0), Seq((2,SKU(1)), (1,SKU(3)))),
  2L -> Order(2, Money(200.0), Seq((1,SKU(4)))),
  // 3L -> Order(3, Money(300.0), Seq((8,SKU(1)), (1,SKU(2)))),
  4L -> Order(4, Money(400.0), Seq((3,SKU(5)), (2,SKU(6)))),
  5L -> Order(5, Money(500.0), Seq()))

// Service invocation that uses a future to run asynchronously.
def getAccount(id: Long): Future[Account] = Future {
  // Contact service, run a DB query, etc...
  accountDB(id)
}

// Service invocation that uses a future to run asynchronously.
def getOrders(ids: Seq[Long]): Future[Seq[Order]] = Future {
  // Contact service, run a DB query, etc...
  ids map orderDB
}

// process orders for accounts asynchronously:
def ordersForAccount(accountId: Long): Future[Seq[Order]] = for {
  account <- getAccount(accountId)
  orders  <- getOrders(account.orderIds)
} yield orders

println("Expect an error for account #2:\n")

Seq(1,2) map { accountId =>
  val future = ordersForAccount(accountId)
  future.onSuccess {
    case orders => println(s"Account #$accountId: $orders")
  }
  future.onFailure {
    case exception => println(s"Account #$accountId: Failed to process orders: $exception")
  }
}
