import shapes._, scala.actors.Actor._

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
