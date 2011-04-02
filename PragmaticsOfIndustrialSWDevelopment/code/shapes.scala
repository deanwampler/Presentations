package shapes

case class Point(x: Double, y: Double)
trait Shape
case class Circle(center: Point, radius: Double) extends Shape
case class Square(lowerLeft: Point, upperRight: Point) extends Shape
