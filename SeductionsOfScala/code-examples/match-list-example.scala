case class Complex(val real: Double, val imaginary: Double) {
  def +(that: Complex) = 
    new Complex(real + that.real, imaginary + that.imaginary)
  def -(that: Complex) = 
    new Complex(real - that.real, imaginary - that.imaginary)
  def unary_- = 
    new Complex(-real, imaginary)
}

val l = Complex(1,2) :: Complex(1.1,2.2) ::
        (1,2,3) :: (3,2,1) :: Nil

def printList[A](l: List[A]): Unit = l match {
  case head :: tail =>
    print(head+", "); printList(tail)
  case Nil =>  // empty
}
printList(l)
