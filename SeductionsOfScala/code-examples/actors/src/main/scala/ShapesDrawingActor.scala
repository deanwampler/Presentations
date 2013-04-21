package shapes

import akka.actor.Actor

class ShapesDrawingActor extends Actor {
  def receive = {
    case s: Shape ⇒
      print("-> ")
      s.draw()
      sender ! "Shape drawn."

    case "exit" ⇒
      println("-> exiting...")
      sender ! "good bye!"

    case x ⇒
      println("-> Error: " + x)
      sender ! s"Unknown message: $x"
  }
}
