package shapes

import akka.actor._
import akka.actor.Actor._

object ShapesDrawingDriver {
  def main2(args: Array[String]) {
    val driver = actorOf[ShapesDrawingDriver].start()
    driver ! "go!"
  }
}
class ShapesDrawingDriver extends Actor {
  val drawer = actorOf[ShapeDrawingActor].start()
  def receive: PartialFunction[Any, Unit] = {
    case "go!" =>
      drawer ! Circle(Point(0.0, 0.0), 1.0)
      drawer ! Rectangle(Point(0.0, 0.0), 2, 5)
      drawer ! 3.14159
      drawer ! "exit"
    case "good bye!" =>
      println("<- cleaning up...")
      drawer.stop()
      self.stop()
    case msg =>
      println("<- " + msg)
  }
}

// -> drawing: Circle(Point(0.0,0.0),1.0)
// -> drawing: Rectangle(Point(0.0,0.0),2.0,5.0)
// -> Error: 3.14159
// -> exiting...
// <- Shape drawn.
// <- Shape drawn.
// <- Unknown message: 3.14159
// <- cleaning up...
