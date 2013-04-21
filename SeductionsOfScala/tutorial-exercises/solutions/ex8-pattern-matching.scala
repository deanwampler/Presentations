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
val buff2 = new StringBuffer
l foreach { 
  case Rational(n,d) => buff2.append("Rational(" + n + "," + d + ")")
  case x => buff2.append("Something else: " + x)
}
buff2.toString is "Rational(2,2)Rational(3,1)Something else: Some(hello!)Something else: (1,2,3)Something else: None"

// Next, add a clause that FIRST looks for Rationals with denominators == 1. 
val buff3 = new StringBuffer
l foreach {
  case Rational(n,1) => buff3.append("Rational: num = " + n + ", denom = 1")
  case Rational(n,d) => buff3.append("Rational(" + n + "," + d + ")")
  case x => buff3.append("Something else: " + x)
}
buff3.toString is "Rational(2,2)Rational: num = 3, denom = 1Something else: Some(hello!)Something else: (1,2,3)Something else: None"

// Finally, add case clauses for Tuple elements, using the literal syntax
// "(a,b,c)", case clauses for Some and None values. In these clauses,
// extract the tuple and Some elements and print them.
val buff4 = new StringBuffer
l foreach {
  case Rational(n,1) => buff4.append("Rational: num = " + n + ", denom = 1")
  case Rational(n,d) => buff4.append("Rational(" + n + "," + d + ")")
  case (x,y,z) => buff4.append("Tuple3(" + x + "," + y + "," + z + ")")
  case Some(x) => buff4.append("Some: " + x)
  case None => buff4.append("None")
  case x => buff4.append("Something else: " + x)
}
buff4.toString is "Rational(2,2)Rational: num = 3, denom = 1Some: hello!Tuple3(1,2,3)None"

// EXTRA: Use pattern matching on List elements, as shown in the slides,
// to implement a recursive function that reverses a list.
// This is easy with a cheat, or a bit tricky otherwise. We'll show three ways:
//   1. Call list.reverse ;)
//   2. Use a nested function.
//   3. Use List's own fold method.
def lreverse1[A](l: List[A]) = l.reverse
def lreverse2[A](l: List[A]) = {
  def rev(l2: List[A], l1: List[A]):List[A] = l1 match {
    case head :: tail => rev(head :: l2, tail)
    case Nil => l2
  }
  rev(Nil, l)
}
def lreverse3[A](l: List[A]) = l.foldLeft(List.empty[A])((l2,x) => x :: l2)

val expected = List(None, (1,2,3), Some("hello!"), Rational(3,1), Rational(2,2))
lreverse1(l) is expected
lreverse2(l) is expected
lreverse3(l) is expected

println("Success!")