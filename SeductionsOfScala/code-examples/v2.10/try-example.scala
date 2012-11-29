// Try, Success, and Failure
// New to Scala 2.10 (part of SIP-14, etc.)
// This example adapted from the Try scaladoc page:
// http://www.scala-lang.org/api/milestone/index.html#scala.util.Try

import scala.util.{Try, Success, Failure}

def divide (dividend: Int, divisor: Int): Try[Int] = {
  val dividendTry = Try(dividend)
  val divisorTry = Try(divisor)
  val problem = dividendTry.flatMap(x => divisorTry.map(y => x/y))
  problem match {
    case Success(v) =>
      println("Result of " + dividendTry.get + "/"+ divisorTry.get +" is: " + v)
      Success(v)
    case Failure(e) =>
      println("You must've divided by zero or entered something that's not an Int. Try again!")
      println("Info from the exception: " + e.getMessage)
      Failure(e)
  }
}

println(divide(20, 5))
println(divide(20, 0))
