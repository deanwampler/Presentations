// Exercise 6: Pattern Matching
// Using your Rational case class, complete the following code to 
// match separately on Rationals with 1.0 as the denominator, 
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

println("pass 1:")
l foreach { _ match {
  case Rational(n,d) => println("Rational(" + n + "," + d + ")")
  case x => println("Something else: " + x)
}}

// We can actually simplify this. Recall the slides said that
//   { case ....; case ...; .... }
// is the literal syntax for anonymous Partial Functions.
// This means Scala can infer the "_ match {}" for the outer {}:
println("pass 1b:")
l foreach { 
  case Rational(n,d) => println("Rational(" + n + "," + d + ")")
  case x => println("Something else: " + x)
}

// Next, add a clause that FIRST looks for Rationals with denominators == 1. 
println("pass 2:")
l foreach {
// case ???
  case Rational(n,d) => println("Rational(" + n + "," + d + ")")
  case x => println("Something else: " + x)
}

// Finally, add case clauses for Tuple elements, Some, and None values, 
// where the nested elements in the Tuples and the Somes are extracted:
println("pass 3:")
l foreach {
// case ???
  case Rational(n,d) => println("Rational(" + n + "," + d + ")")
// case ???
  case x => println("Something else: " + x)
}
