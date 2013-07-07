package shapes
import scala.math.{ Pi, hypot, sqrt }

case class Point(x: Double, y: Double)

abstract class Shape() {
  def draw() = println("drawing: " + this)
  def area: Double
}

case class NegativeValue(d: Double, name: String = "<unknown>") extends RuntimeException(
  "Nonnegative value required, but " + d + " given for the " + name + ".")

case class Circle(center: Point, radius: Double) extends Shape {
  if (radius < 0.0) throw NegativeValue(radius, "radius")

  def area: Double = Pi * radius * radius
}

/** Assume the Rectangle isn't rotated. */
case class Rectangle(lowerLeft: Point, h: Double, w: Double) extends Shape {
  if (h < 0.0) throw NegativeValue(h, "height")
  if (w < 0.0) throw NegativeValue(w, "width")

  def area: Double = h * w
}

/** But allow the Rectangle to be rotated. */
case class Triangle(point1: Point, point2: Point, point3: Point) extends Shape {

  def area: Double = {
    // Heron's method (http://en.wikipedia.org/wiki/Triangle#Computing_the_area_of_a_triangle)
    val l12 = hypot(point1.x - point2.x, point1.y - point2.y)
    val l23 = hypot(point2.x - point3.x, point2.y - point3.y)
    val l31 = hypot(point3.x - point1.x, point3.y - point1.y)
    val s = (l12 + l23 + l31) / 2.0
    sqrt(s * (s - l12) * (s - l23) * (s - l31))
  }
}
