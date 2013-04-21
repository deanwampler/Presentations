// Exercise 10: Implement Your Own whileTrue Control
//
// Here is the declaration of the "DSL" function we want:
// def whileTrue(condition: => Boolean)(block: => Unit): Unit
// Note that each argument is a by-name parameter.
// Use recursion.

/* ---------------------- */
/* Define whileTrue here: */

def whileTrue(condition: => Boolean)(block: => Unit): Unit =
  if (condition) {
    block
    whileTrue(condition)(block)
  }
/* ---------------------- */

// We'll use it this way:

var accumulator = List[Int]()  // non-FP hack :)
var i = 0
whileTrue(i < 10) {
  // For mutable lists, you can use this "+=" trick:
  accumulator ::= i
  i += 1
}

import CheapTests._

// Why the call to reverse??
accumulator.reverse is List(0,1,2,3,4,5,6,7,8,9)


// Here is a slightly different form that uses a nested method. Note that
// the condition and block are still in scope inside the nested method:
def whileTrue2(condition: => Boolean)(block: => Unit): Unit = {
  def wt: Unit = if (condition) {
    block
    wt
  }
  wt
}

var accumulator2 = List[Int]()
var i2 = 0
whileTrue2(i2 < 20) {
  accumulator2 ::= i2
  i2 += 1
}

// Why the call to reverse??
accumulator2.reverse is List(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19)

println("Success!")


