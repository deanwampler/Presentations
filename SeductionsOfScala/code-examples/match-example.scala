case class Complex(val real: Double, val imaginary: Double) {
  def +(that: Complex) = 
    new Complex(real + that.real, imaginary + that.imaginary)
  def -(that: Complex) = 
    new Complex(real - that.real, imaginary - that.imaginary)
  def unary_- = 
    new Complex(-real, imaginary)
}

val l = Complex(1,2) :: Complex(1.1,2.2) :: (1,2,3) :: (3,2,1) :: Nil

l foreach { _ match {
  case Complex(1,i) => println("i="+i)
  case Complex(r,i) => println(r+","+i)
  case (3,2,_) => println("(3,2,?)")
  case (a,b,c) => 
    println("("+a+","+b+","+c+")")
  case Nil => // #5 (nothing)
}}

// Becuase we don't actually care about the value passed to the foreach (we use
// a "_" for it), this code is IDENTICAL to this:

l foreach { 
  case Complex(1,i) => println("i="+i)
  case Complex(r,i) => println(r+","+i)
  case (3,2,_) => println("(3,2,?)")
  case (a,b,c) => 
    println("("+a+","+b+","+c+")")
  case Nil => // #5 (nothing)
}