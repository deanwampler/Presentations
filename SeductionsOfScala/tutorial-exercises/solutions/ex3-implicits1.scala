// Exercise 3: Implicits - Units of feet vs. meters.
// We'll look at two important uses of implicits:
//   1. Type conversions.
//   2. "Extension methods" or "type classes"; two names used in different 
//      languages for techniques that allow you to add new methods to types.
// 
// We'll accomplish both in one step. Let's define some basic types for
// encapsulating Doubles as feet or meters. We'll introduce an implicit to
// convert a "bare" Double to one or the other, as appropriate.
// We'll then use separate implicits to convert each type into the other.
//
// See also our little DSL for testing in "cheap-tests.scala"
//
// We will use a Scala v2.10 feature; "String Interpolation". In the
// following string, the prefix 's' in front of the string enables interpolation
// and the "$length" means substituted the string representation for the variable
// "length":
//   s"$length meters"
// You'll see this in the Meters.toString method below, which could have also 
// been written in either of the following ways:
//   override def toString = ""+length+" meters"
//   override def toString = "%f meters".format(length)
//
// Now, here are the two Types, for starters:

case class Meters(val length: Double) {
  def +(m: Meters): Meters = new Meters(length + m.length)
  override def toString = s"$length meters"
}

case class Feet(val length: Double) {
  def +(f: Feet): Feet   = new Feet(length + f.length)
  override def toString = s"$length feet"
}

// What if we want to "lift" a Double into a Feet or Meters?
// Write an "implicit class" that takes a Double argument in the
// constructor and provides two messages "feet" and "meters" that
// return a Feet and Meters object, respectively, that wraps the
// double. Follow the example on the "v2.10 Implicit Classes" slide
// that shows the declaration of "implicit class ArrowAssoc".
// finally, note that the implicit conversations have to be defined
// within an enclosing type, object or class, so their scope is 
// constrained. This avoids one potential disadvantage of implicits,
// that they get applied unexpectedly in some global scope!

object FeetMetersConverters {

  /* ------------------------------- */
  /* Define the implicit class here: */

  implicit class ToFeetMeters(d: Double) {
    def feet: Feet = new Feet(d)
    def meters: Meters = new Meters(d)
  }

  /* ------------------------------- */

  // The next logical problem is to convert Feet to Meters and
  // vice-versa. We can use "implicit vals" for this. Follow the
  // example in the "Type Conversion" slides to define two
  // vals that are functions, one which converts Feet to Meters and
  // the other which converts the other direction.
  // Use the feetToMeters and metersToFeet helper functions above.
  // NOTE: There are 3.28084 feet in a meter.

  val FEET_IN_ONE_METER = 3.28084

  /* ----------------------------------- */
  /* Define implicit val functions here: */

  implicit val toMeters = (f: Feet) => new Meters(f.length/FEET_IN_ONE_METER)
  implicit val toFeet = (m: Meters) => new Feet(m.length*FEET_IN_ONE_METER)

  /* ----------------------------------- */

}

import FeetMetersConverters._
import CheapTests._

10.0.feet   is Feet(10.0)
10.0.meters is Meters(10.0)
10.0.meters isnot Feet(10.0)
10.0.feet   isnot Meters(10.0)

10.0.feet   + 10.0.meters is Feet(  10.0 + 10.0*FEET_IN_ONE_METER)
10.0.meters + 10.0.feet   is Meters(10.0 + 10.0/FEET_IN_ONE_METER)
10.0.feet   + 10.0.feet   isnot Meters(20.0/FEET_IN_ONE_METER)
10.0.meters + 10.0.meters isnot Feet(  20.0*FEET_IN_ONE_METER)

println("Success!")