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

    case other ⇒
      println("-> Error: " + other)
      sender ! s"Unknown message: $other"
  }
}
