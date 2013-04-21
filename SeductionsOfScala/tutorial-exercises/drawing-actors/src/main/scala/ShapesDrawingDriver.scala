package shapes

import akka.actor.{ Props, Actor, ActorRef, ActorSystem }
import com.typesafe.config._

// Message used only in this file:
case class Start(drawer: ActorRef)

object ShapesDrawingDriver {
  def main(args: Array[String]) {
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val driver = system.actorOf(Props[ShapesDrawingDriver], "drawingService")
    val drawer = system.actorOf(Props[ShapesDrawingActor], "drawingActor")
    driver ! Start(drawer)
  }
}

class ShapesDrawingDriver extends Actor {
  var drawer: Option[ActorRef] = None

  def receive = {
    case Start(drawerActor) ⇒
      drawer = Some(drawerActor)
      drawerActor ! Circle(Point(0.0, 0.0), 1.0)
      drawerActor ! Rectangle(Point(0.0, 0.0), 2, 5)
      drawerActor ! 3.14159
      drawerActor ! "exit"
    case "good bye!" ⇒
      println("<- cleaning up...")
      context.system.shutdown()
    case msg ⇒
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
