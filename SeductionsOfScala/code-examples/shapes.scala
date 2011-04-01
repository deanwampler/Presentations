// Same file as in code/functions. Copied here for convenience.
package shapes {  
  case class Point(x: Double, y: Double)  

  abstract case class Shape() { def draw() = println(this) }  
  case class Circle(center: Point, radius: Double) extends Shape  
  case class Rectangle(lowerLeft: Point, h: Double, w: Double) 
      extends Shape
}
