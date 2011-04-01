// Exercise 7: Pattern Matching
// Using your Rational case class, complete the following code to 
// match separately on Rationals with 1.0 as the denominator, 
// all other Rationals, and all other cases.
// Call println with a separate message for each.
// Extra credit: extract the Some values.

// (Fixed some typos in the slides)
val l = List(Rational(2,2), Rational(3,1), Some("hello!"), (1,2,3), None)
l foreach { 
  // case statements
}

