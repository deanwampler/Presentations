package shapes

case class Point(x: Double, y: Double)

abstract class Shape() {
  def draw() = println("drawing: " + this)
}
case class Circle(center: Point, radius: Double) extends Shape

case class Rectangle(lowerLeft: Point, h: Double, w: Double)
  extends Shape

case class Triangle(point1: Point, point2: Point, point3: Point)
  extends Shape
