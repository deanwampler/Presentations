case class Point(x: Double, y: Double)
trait Shape
case class Circle(center: Point, radius: Double) extends Shape
case class Rectangle(lowerLeft: Point, upperRight: Point) extends Shape

case class DrawNotUnsupported(a: Any) 
  extends RuntimeException(a.toString + " does not support draw")
  
class Drawable(shape: Shape) {
  def draw = shape match {
    case c: Circle => doDraw(c)
    case r: Rectangle => doDraw(r)
    case _ => throw DrawNotUnsupported(shape) 
  }
  private
  def doDraw(s: Shape) = println(s)
}

object Drawable {
  implicit def shapeToDrawable(s: Shape) = new Drawable(s)
}
import Drawable._

Circle(Point(0.0, 0.0), 5.0).draw
Rectangle(Point(0.0, 0.0), Point(4.0, 2.0)).draw

case class Triangle(one: Point, two: Point, three: Point) extends Shape
Triangle(Point(0.0, 0.0), Point(2.0, 0.0), Point(1.0, 1.0)).draw
// => Circle(Point(0.0,0.0),5.0)
// => Rectangle(Point(0.0,0.0),Point(4.0,2.0))
// => Main$$anon$1$DrawNotUnsupported: Triangle(Point(0.0,0.0),Point(2.0,0.0),Point(1.0,1.0)) does not support draw
// =>   ...