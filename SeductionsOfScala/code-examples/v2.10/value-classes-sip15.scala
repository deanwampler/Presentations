// Value classes 
// New to Scala 2.10 (SIP-13)
// Let's use them to wrap Doubles with feet and meters units.
// How to could have prevented a Mars landers from pancaking...
// https://docs.scala-lang.org/sips/pending/value-classes.html

class Meters(val length: Double) extends AnyVal {
  def +(m: Meters): Meters = new Meters(length + m.length)
  ov/erride def toString = s"$length meters"
}

class Feet(val length: Double) extends AnyVal {
  def +(f: Feet): Feet   = new Feet(length + f.length)
  override def toString = s"$length feet"
}

implicit class toFeetMeters(d: Double) {
  def feet: Feet = new Feet(d)
  def meters: Meters = new Meters(d)
}
implicit val feetToMeters = (f: Feet) => new Meters(f.length/3.28084)
implicit val metersToFeet = (m: Meters) => new Feet(m.length*3.28084)

println("meters: ")
val distance1 = 2.0.meters + 3.0.feet
val expected1 = 2.0 + (3.0 / 3.28084)
val diff1 = math.abs(distance1.length - expected1) < 0.00001
println(s" distance $distance1 == $expected1? $diff1")

println("feet: ")
val distance2 = 2.0.feet + 3.0.meters
val expected2 = 2.0 + (3.0 * 3.28084)
val diff2 = math.abs(distance2.length - expected2) < 0.00001
println(s" distance $distance2 == $expected2? $diff2")
