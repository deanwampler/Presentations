// Exercise 6: A Rational Number Case Class
// Convert your previous Rational class (from Ex. 2) to a case class.
// Create objects using Rational.apply.
// Would a second apply method be useful?
// Play with the generated toString, equals, hashCode and field accessor methods.
// You can run this file with "scala ex6-case-class-rationals-solution.scala"

// Here is a case-class solution:
case class Rational(numerator: Int, denominator: Int) {
  def + (r2: Rational) = 
    new Rational(numerator * r2.denominator + r2.numerator * denominator, 
                 denominator * r2.denominator)

  def - (r2: Rational) = 
    new Rational(numerator * r2.denominator - r2.numerator * denominator, 
                 denominator * r2.denominator)

  def * (r2: Rational) = 
    new Rational(numerator * r2.numerator, denominator * r2.denominator)

  def / (r2: Rational) = 
    new Rational(numerator * r2.denominator, denominator * r2.numerator)
  
  // We would actually prefer our custom toString method, as it better matches
  // how we think about Rationals, but for learning purposes, we'll use the 
  // generated method.               
  // override def toString = numerator + "/" + denominator
}

val r1  = Rational(22, 7)
val r1b = Rational(22, 7)
val r2  = Rational(11, 3)
println("r1 == r1?  " + (r1 == r1))
println("r1 == r1b? " + (r1 == r1b))
println("r1 == r2?  " + (r1 == r2))
println("r1 equals r1?  " + (r1 equals r1))
println("r1 equals r1b? " + (r1 equals r1b))
println("r1 equals r2?  " + (r1 equals r2))
println("r1 eq r1?  " + (r1 eq r1))   // address equality (same object)
println("r1 eq r1b? " + (r1 eq r1b))
println("r1 eq r2?  " + (r1 eq r2))

// The following statements are the same as before, but the output is different
// when the new toString method is used:
println("r1: " + r1)
println("r2: " + r2)
println("r1.numerator:   " + r1.numerator)
println("r1.denominator: " + r1.denominator)
println("r2.numerator:   " + r2.numerator)
println("r2.denominator: " + r2.denominator)
println("r1 + r2: " + (r1 + r2))
println("r2 + r1: " + (r2 + r1))
println("r1 - r2: " + (r1 - r2))
println("r2 - r1: " + (r2 - r1))
println("r1 * r2: " + (r1 * r2))
println("r2 * r1: " + (r2 * r1))
println("r1 / r2: " + (r1 / r2))
println("r2 / r1: " + (r2 / r1))

// We might like a second Rational.apply method for the case when the denominator
// is one. To do this, we explicitly define the companion object with the second
// apply method, but we still get the "rest" of the companion object we had before
// from the case keyword:

case class Rational2(numerator: Int, denominator: Int) {
  // same as before
}
object Rational2 {
  // second apply method.
  def apply(numerator: Int) = new Rational2(numerator, 1)
}

val r3 = Rational2(22, 7)   // use first apply method
val r4 = Rational2(11)      // use second apply method
println("r3.numerator:   " + r3.numerator)
println("r3.denominator: " + r3.denominator)
println("r4.numerator:   " + r4.numerator)
println("r4.denominator: " + r4.denominator)

