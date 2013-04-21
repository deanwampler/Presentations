// Exercise 8: Pattern Matching
// Using the Rational case class from ex. 2, complete the following code 
// to match separately on Rationals with 1.0 as the denominator, 
// all other Rationals, and all other cases.
// Call println with a separate message for each.
// Extra credit: extract the Some values.

import CheapTests._

// First, here is the definition of Rational, where have omitted the operator 
// methods for simplicity. Since we have no methods, we have omitted the entire
// class body!
case class Rational(numerator: Int, denominator: Int) 

// Here is our first match code. It matches on any Rational, then everything else.

val l = List(Rational(2,2), Rational(3,1), Some("hello!"), (1,2,3), None)

val buff1 = new StringBuffer
l foreach { _ match {
  case Rational(n,d) => buff1.append("Rational(" + n + "," + d + ")")
  case x => buff1.append("Something else: " + x)
}}
buff1.toString is "Rational(2,2)Rational(3,1)Something else: Some(hello!)Something else: (1,2,3)Something else: None"

// We can actually simplify this. Recall what the slides said about
//   { case ....; case ...; .... }
// being the literal syntax for anonymous Partial Functions.
// This means Scala can infer the "_ match {}" for the outer {}.
// Copy and modify the previous foreach expression to eliminate
// the "_ match", as in the slides:
// val buff2 = new StringBuffer
// l foreach {
//   ??? 
// }
// buff2.toString is "Rational(2,2)Rational(3,1)Something else: Some(hello!)Something else: (1,2,3)Something else: None"

// Next, add a clause that FIRST looks for Rationals with denominators == 1. 
// val buff3 = new StringBuffer
// l foreach {
//   ???
// }
// buff3.toString is "Rational(2,2)Rational: num = 3, denom = 1Something else: Some(hello!)Something else: (1,2,3)Something else: None"

// Finally, add case clauses for Tuple elements, using the literal syntax
// "(a,b,c)", case clauses for Some and None values. In these clauses,
// extract the tuple and Some elements and print them.
// val buff4 = new StringBuffer
// l foreach {
//   ???
// }
// buff4.toString is "Rational(2,2)Rational: num = 3, denom = 1Some: hello!Tuple3(1,2,3)None"

// EXTRA: Use pattern matching on List elements, as shown in the slides,
// to implement a recursive function that reverses a list.

println("Success!")