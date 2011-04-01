class Complex(val real: Double, val imaginary: Double) {
  def +(that: Complex) = 
    new Complex(real + that.real, imaginary + that.imaginary)
  def -(that: Complex) = 
    new Complex(real - that.real, imaginary - that.imaginary)
  def unary_- = 
    new Complex(-real, imaginary)
  override def toString() = "("+real+", "+imaginary+")"
}
