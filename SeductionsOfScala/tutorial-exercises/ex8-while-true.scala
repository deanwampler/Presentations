// Exercise 8: Implement Your Own whileTrue Control
//
// Here is the declaration of the "DSL" function we want:
// def whileTrue(condition: => Boolean)(block: => Unit): Unit
// Note that each argument is a by-name parameter.
// Use recursion.

/* ---------------------- */
/* Define whileTrue here: */
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


println("Success!")
