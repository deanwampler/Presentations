package shapes

case class Point(x: Double, y: Double)
trait Shape { 
  def draw: Unit
}
case class Circle(center: Point, radius: Double) extends Shape { 
  def draw: Unit = // draw the shape
}
case class Square(lowerLeft: Point, upperRight: Point) extends Shape { 
  def draw: Unit = // draw the shape
}
