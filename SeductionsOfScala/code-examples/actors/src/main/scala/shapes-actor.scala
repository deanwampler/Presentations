package shapes

import akka.actor._
import shapes._

class ShapeDrawingActor extends Actor {  
  def receive: PartialFunction[Any, Unit] = {
    case s:Shape => 
      print("-> ")
      s.draw()
      self.reply("Shape drawn.")
    
    case "exit"  =>
      println("-> exiting...")
      self.reply("good bye!")
    
    case x  => 
      println("-> Error: " + x)
      self.reply("Unknown message: " + x)
  }
}
