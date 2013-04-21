// Exercise 9: Covariant and Contravariant Typing with Functions.

import CheapTests._

// First, dummy type hierarchies and functions to illustrate:

class I1(val one: Int) {
    override def toString = s"I1($one)"
}
class I2(one: Int, val two: Int) extends I1(one) {
    override def toString = s"I2(${super.toString},$two)"
}
class I3(one: Int, two: Int, val three: Int) extends I2(one, two) {
    override def toString = s"I3(${super.toString},$three)"
}

class S1(val one: String) {
    override def toString = s"S1($one)"
}
class S2(one: String, val two: String) extends S1(one) {
    override def toString = s"S2(${super.toString},$two)"
}
class S3(one: String, two: String, val three: String) extends S2(one, two) {
    override def toString = s"S3(${super.toString},$three)"
}

def convertI2(i2: I2)(f: I2 => S2): S2 = f(i2)

// Functions that convert an In to its "sibling".
val f11: I1 => S1 = (i1:I1) => new S1(i1.one.toString)
val f22: I2 => S2 = (i2:I2) => new S2(i2.one.toString, i2.two.toString)
val f33: I3 => S3 = (i3:I3) => new S3(i3.one.toString, i3.two.toString, i3.three.toString)

// Functions that "cross-convert"...
val f13: I1 => S3 = (i1:I1) => new S3(i1.one.toString, "", "")
val f31: I3 => S1 = (i3:I3) => new S1(i3.one.toString)

val i1 = new I1(1)
val i2 = new I2(1,2)
val i3 = new I3(1,2,3)

// The lines that are commented don't compile. Uncomment them and look at the errors.
// convertI2(i1)(f11).toString is "S1(1)"
convertI2(i2)(f22).toString is "S2(S1(1),2)"
// convertI2(i3)(f33).toString is "S3(S2(S1(1),2),3)"
convertI2(i2)(f13).toString is "S3(S2(S1(1),),)"
// convertI2(i2)(f31).toString is "S2(S1(1),2)"

println("Success!")
