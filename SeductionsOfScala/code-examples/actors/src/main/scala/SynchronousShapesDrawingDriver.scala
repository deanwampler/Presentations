package shapes

import akka.actor._
import akka.actor.Actor._

object SyncShapesDrawingDriver {
  def main(args: Array[String]) {
    val driver = actorOf[SyncShapesDrawingDriver].start()
    driver ! "go!"
  }
}
class SyncShapesDrawingDriver extends Actor {
  val drawer = actorOf[ShapeDrawingActor].start ()
  protected def shutdown: Unit = {
    drawer.stop()
    self.stop()
  }

  def receive: PartialFunction[Any,Unit] = {
    case "go!" =>
      List(
				Circle(Point(0.0,0.0), 1.0),
				Rectangle(Point(0.0,0.0), 2, 5),
				3.14159,
				"exit") foreach { message =>
				  (drawer !! message) match {
						case None => //throw new RuntimeException("TIMEOUT")
						case Some(reply) =>
							println("<- "+reply)
							if (reply == "good bye!") {
								shutdown
							}
					}
			}
    case msg =>
      println("<- error: Unexpected message received: "+msg)
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
