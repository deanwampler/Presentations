// Futures and Promises 
// New to Scala 2.10 (SIP-14)
// https://docs.scala-lang.org/sips/pending/futures-promises.html

import scala.concurrent.{future, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}

def print_time(msg: String) {
  printf("%d: %s\n", System.currentTimeMillis, msg)
}

def lookBusy(l: Long): Double = 
  if (l > 0) {
    scala.math.random
    lookBusy(l-1)
  } else {
    scala.math.random
  }

// Arbitrary size. Try increasing it until you see the
// "onComplete" and "onFailure" messages appear AFTER the
// "after defining" messages.
// (You won't see any "onSuccess" messages...)
val BUSY_COUNT = 10000000

def doSomethingImportant: List[String] = {
  lookBusy(BUSY_COUNT)
  "Hello" :: "World" :: Nil
}

case class UnimportantFailure(msg: String) extends RuntimeException(msg)

def failSomethingUnimportant: List[String] = {
  lookBusy(BUSY_COUNT)
  throw new UnimportantFailure("from failSomethingUnimportant().")
}

val f1: Future[List[String]] = future {
  doSomethingImportant
}
print_time("f1 = future...")

f1 onComplete {
  case Success(list) => print_time(s"f1 onComplete - Success: $list")
  case Failure(thrown) => print_time(s"f1 onComplete - Error: $thrown.getMessage")
}
print_time("f1 after defining onComplete.")

val f2: Future[List[String]] = future {
  failSomethingUnimportant
}
print_time("f2 = future...")

f2 onSuccess {
  case list => throw new RuntimeException(s"f2 onSuccess should not have been triggered! list = $list")
}
f2 onFailure {
  case UnimportantFailure(msg) => 
    print_time(s"f2 onFailure: UnimportantFailure($msg)")  
}
print_time("f2 after defining onSuccess and onFailure.")
