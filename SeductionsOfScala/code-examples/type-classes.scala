case class Point(x:Double, y:Double)
abstract class Shape {
//	def draw():Unit
}
case class Circle(center:Point, radius: Double) extends Shape {
//	def draw() = println(this)
}
case class Rectangle(ll:Point, height: Double, width: Double) extends Shape {
//	def draw() = println(this)
}

// Type Class
trait Drawable { def draw():Unit }
class DrawableLike(s:Shape) extends Drawable {
	def draw() = s match {
		case c:Circle => println(c)
		case r:Rectangle => println(r)
		case _ => throw new RuntimeException("Unknown shape!")
	}
}

object Drawable {
	implicit def toDrawable(s:Shape) = new DrawableLike(s)
}

import Drawable._

Circle(Point(1.0,2.0),5.0).draw()

//...

def drawAll[A <: { def draw():Unit }](list: List[A]) = list foreach {_.draw()}

// This only works if the draw method is still in the shape hierarchy. The implicits
// aren't applied BEFORE doing the type checking!
//drawAll(List(Circle(Point(1.0,2.0),5.0), Rectangle(Point(0.0,0.0), 1.0, 2.0)))


