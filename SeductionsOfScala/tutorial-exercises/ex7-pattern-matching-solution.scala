// Exercise 7: Pattern Matching
// Using your Rational case class, complete the following code to 
// match separately on Rationals with 1.0 as the denominator, 
// all other Rationals, and all other cases.
// Call println with a separate message for each.
// Extra credit: extract the Some values.
// You can run this file with "scala ex7-pattern-matching-solution.scala"

// First, here is the definition of Rational, where have omitted the operator 
// methods for simplicity. Since we have no methods, we have omitted the entire
// class body!
case class Rational(numerator: Int, denominator: Int) 

val l = List(Rational(2,2), Rational(3,1), Some("hello!"), (1,2,3), None)
println("pass 1:")
l foreach { _ match {
  case Rational(n,1) => println("Rational: num = " + n + ", denom = 1")
  case Rational(n,d) => println("Rational(" + n + "," + d + ")")
  case x => println("Something else: " + x)
}}

// Recall that the "_ match {...}" outer block is unnecessary, as the compiler
// will supply that part, creating a "partial function", if you provide one or
// case statements:
println("pass 2:")
l foreach {
  case Rational(n,1) => println("Rational: num = " + n + ", denom = 1")
  case Rational(n,d) => println("Rational(" + n + "," + d + ")")
  case x => println("Something else: " + x)
}

// Finally, let's also match on Tuple elements and extract the Some values:
println("pass 3:")
l foreach {
  case Rational(n,1) => println("Rational: num = " + n + ", denom = 1")
  case Rational(n,d) => println("Rational(" + n + "," + d + ")")
  case (x,y,z) => println("Tuple3(" + x + "," + y + "," + z + ")")
  case Some(x) => println("Some: " + x)
  case None => println("None")
  case x => println("Something else: " + x)
}
