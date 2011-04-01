def checkForUpdates() = {
  println("Did Santa arrive yet?")
  println("Guess not...")
}

class Times(i: Int) {
  def times = i
}

object Repeater {
  def repeat(i:Int)(f: => Unit) = 
    for (j <- 1 to i) f
   
  implicit def int2Times(i: Int) = new Times(i)
}

import Repeater._

repeat (10 times) {
  checkForUpdates()
}
