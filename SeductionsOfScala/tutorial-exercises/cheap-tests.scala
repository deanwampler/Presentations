object CheapTests {
  implicit class CheapTestsForDoubles(actual: Double) {

    val TOLERANCE = 0.00001

    def is(expected: Double, within: Double = TOLERANCE):Unit = 
        (math.abs(expected - actual) <= TOLERANCE) match {
      case true =>
      case false => 
        println("ERROR! Expected %f vs. actual %f not within tolerance %f".format(expected, actual, within))
        sys.exit(1)
    }

    def isnot(expected: Double, within: Double = TOLERANCE):Unit = 
        (math.abs(expected - actual) > TOLERANCE) match {
      case true =>
      case false => 
        println("ERROR! Expected the difference of the expected %f vs. the actual %f to be larger than tolerance %f".format(expected, actual, within))
        sys.exit(1)
    }
  }

  implicit class CheapTestsForEverythingElse[T](actual: T) {

    def is[T](expected: T):Unit = (expected == actual) match {
      case true =>
      case false => 
        println("ERROR! Expected \"%s\", but got \"%s\"".format(expected, actual))
        sys.exit(1)
    }

    def isnot[T](expected: T):Unit = (expected != actual) match {
      case true =>
      case false => 
        println("ERROR! Expected \"%s\" to not equal \"%s\"".format(expected, actual))
        sys.exit(1)
    }
  }
}