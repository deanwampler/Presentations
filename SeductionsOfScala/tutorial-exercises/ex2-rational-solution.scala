// Exercise 2: Solution
// Create a class for Rational Numbers, which represent division of one integer
// by another exactly, rather than computing the actual floating point value, 
// which is subject to round-off errors, etc.
// For example, 22/7 would be the value "new Rational(2,7)", rather than
// 3.142857142857143. (I picked this example because 22/7 is famously close to
// the value of Pi.) Here, 22 is the numerator and 7 is the denominator, in this case.
// Here are the rules for +, -, *, /, where r1 = n1/d2 and r2 = n2/d2:
//   r1 + r2    (n1*d2 + n2*d1)/d1*d2
//   r1 - r2    (n1*d2 - n2*d1)/d1*d2
//   r1 * r2    n1*n2/d1*d2
//   r1 / r2    n1*d2/d1*n2
//
// Create an immutable Rational class.
// Add methods for +, -, toString.
// Bonus: Add method * and /. 
// Test with examples.

// You can run this file with "scala ex2-rational-solution.scala"

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

