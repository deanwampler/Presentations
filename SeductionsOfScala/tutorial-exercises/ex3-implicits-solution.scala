// Exercise 3: Implicits.
// We'll look at two important uses of implicits:
//   1. Type conversions.
//   2. "Extension methods", "type classes"; two names used in different 
//      languages for techniques that allow you to add new methods to types.
// 
// We'll accomplish both in one step. Let's define some basic types for
// encapsulating Doubles as feet or meters. We'll introduce an implicit to
// convert a "bare" Double to one or the other, as appropriate.
// We'll then use separate implicits to convert each type into the other.

// First, you can use these helper methods.

def feetToMeters(feet: Double): Double = feet/3.28084
def metersToFeet(meters: Double): Double = meters*3.28084

// Here are the two Types, for starters:
// We are actually using a Scala v2.10 feature, called Value Types.
// Previously, we couldn't subclass AnyVal, but it turns out to be useful
// problems like ours, where we want to "embellish" the built-in value types,
// that is, the children of AnyVal.
// We are also using another 2.10 feature, called String Interpolation.
// The Meters.toStrin method could have been written in either of the 
// following ways:
//   override def toString = ""+length+" meters"
//   override def toString = "%f meters".format(length)

class Meters(val length: Double) extends AnyVal {
  def +(m: Meters): Meters = new Meters(length + m.length)
  override def toString = s"$length meters"
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
