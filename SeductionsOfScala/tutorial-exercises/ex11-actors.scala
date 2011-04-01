// Exercise 11: Actors
// Starting with the Actors example, modify it to have the driver actor send
// a shape, then wait for a reply, after which it sends the next shape, etc.
// Put in a sleep call or other delay to slow it down.
// Also, change the shape hierarchy; add at least one more shape type.
// Add more “control” messages (in addition to "exit").
// You can run this file with "scala ex11-actors.scala"

// Here is the full code for the example. I've commented out the package 
// declarations, because they aren't allowed in scripts (implementation
// limitation). In a real application, you would probably put these things in
// packages, like we showed in the notes, and put them into separate files for
// compilation:

//package shapes {  
  case class Point(x: Double, y: Double)  

  abstract case class Shape() { def draw() = println(this) }  
  case class Circle(center: Point, radius: Double) extends Shape  
  case class Rectangle(lowerLeft: Point, h: Double, w: Double) extends Shape
//}

//package shapes {
  import scala.actors._, Actor._
//  import shapes._
  object ShapeDrawingActor extends Actor {  
    def act() {
      loop {
        receive {
          case s:Shape => 
            s.draw()
            sender ! "drawn."
          
          case "exit"  =>
            println("exiting...")
            sender ! "good bye!"
            exit
          
          case x  => 
            println("Error: " + x)
            sender ! "Unknown message: " + x
        }
      }
    } 
  }
//}


//import shapes._, 
import scala.actors.Actor._

def sendAndReceive(msg: Any) = {
  ShapeDrawingActor ! msg
  self.receive {
    case msg => println(msg)
  }
}

ShapeDrawingActor.start()
sendAndReceive(Circle(Point(0.0,0.0), 1.0))
sendAndReceive(Rectangle(Point(0.0,0.0), 2, 5))
sendAndReceive(3.14159)
sendAndReceive("exit")

// => Circle(Point(0.0,0.0),1.0)
// => drawn.
// => Rectangle(Point(0.0,0.0),2.0,5.0)
// => drawn.
// => Error: 3.14159
// => Unknown message: 3.14159
// => exiting...
// => good bye!

