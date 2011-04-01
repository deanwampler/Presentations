// Exercise 6: A Rational Number Case Class
// Convert your previous Rational class (from Ex. 2) to a case class.
// Create objects using Rational.apply.
// Would a second apply method be useful?
// Play with the generated toString, equals, hashCode and field accessor methods.
// You can run this file with "scala ex6-case-class-rationals.scala"

// Here is a solution to Ex. 2:
class Rational(val numerator: Int, val denominator: Int) {
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
                 
  override def toString = numerator + "/" + denominator
}

val r1 = new Rational(22, 7)
val r2 = new Rational(11, 3)
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
