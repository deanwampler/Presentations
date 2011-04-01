case class Complex(val real: Double, val imaginary: Double) {
  def +(that: Complex) = 
    new Complex(real + that.real, imaginary + that.imaginary)
  def -(that: Complex) = 
    new Complex(real - that.real, imaginary - that.imaginary)
  def unary_- = 
    new Complex(-real, imaginary)
}

val map = Map(
 "c" -> Complex(1,2),
 "t" -> (1.1,2.2))

println(map.get("c"))
println(map.get("t"))
println(map.get("a"))

println("")

List("c","t","a").foreach { k => 
 println(map.get(k) match {
  case Some((a,b)) => a+":"+b
  case Some(x) => x
  case None => "No value: "+k
})}