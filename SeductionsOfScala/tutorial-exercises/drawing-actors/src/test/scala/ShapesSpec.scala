package shapes

import org.scalatest.{ FunSpec, ShouldMatchers }
import scala.math.Pi

/**
 * These tests don't do anything very exciting, but they illustrate
 * the basics of writing unit tests in Scala with the ScalaTest API.
 * NOte that several DSL styles are available. I'm using a BDD (Behavior
 * Driven Development) style here, like RSpec for Ruby...
 * An alternative testing API is the popular Specs2.
 * We don't even test the Actors here. Akka comes with test support tools
 * that you will want to investigate.
 * Also, when checking mathematical properties like the area calculations,
 * it's FAR BETTER to use ScalaCheck with ScalaTest or Specs2! Why, because
 * ScalaCheck lets you define properties, such as the area formula, and it
 * verifies the properties are satisfied for a generated set of instances,
 * not just the inadequate sample we hard-code here.
 */
class ShapesSpec extends FunSpec with ShouldMatchers {
  case class Toss(s: String) extends RuntimeException(s);
  describe("Circle") {
    it("rejects a negative radius") {
      intercept[NegativeValue] {
        Circle(Point(0.0, 0.0), -1.0)
      }
    }
    it("knows its area") {
      Circle(Point(0.0, 0.0), 1.0).area should be(Pi plusOrMinus 0.001)
      Circle(Point(2.5, 3.1), 2.0).area should be(4.0 * Pi plusOrMinus 0.001)
    }
  }

  describe("Rectangle") {
    it("rejects a negative height") {
      intercept[NegativeValue] {
        Rectangle(Point(0.0, 0.0), 0.0, -1.0)
      }
    }
    it("rejects a negative width") {
      intercept[NegativeValue] {
        Rectangle(Point(0.0, 0.0), -1.0, 0.0)
      }
    }
    it("knows its area") {
      Rectangle(Point(0.0, 0.0), 1.0, 2.0).area should be(2.0 plusOrMinus 0.001)
      Rectangle(Point(2.5, 3.1), 2.0, 3.5).area should be(7.0 plusOrMinus 0.001)
    }
  }

  describe("Triangle") {
    it("knows its area") {
      Triangle(Point(0.0, 0.0), Point(1.0, 0.0), Point(0.0, 1.0)).area should be(0.5 plusOrMinus 0.001)
    }
  }
}
