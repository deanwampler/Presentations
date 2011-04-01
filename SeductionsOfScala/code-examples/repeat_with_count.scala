def checkForUpdates() = {
  println("Did Santa arrive yet?")
  println("Guess not...")
}

def checkForUpdates(index: Int, last: Int) = {
  println("Did Santa arrive yet?")
  if (index != last)
    println("Guess not...")
  else
    println("He's here!!!")
}

class Times(i: Int) {
  def times = i
}

object Repeater {
  def repeat(i:Int)(f: => Unit) = 
    for (j <- 1 to i) f
  
  // Must use a different name:
  def repeat(i:Int)(f: (Int, Int) => Unit) = 
    for (j <- 1 to i) f(j, i)
   
  implicit def int2Times(i: Int) = new Times(i)
}

import Repeater._

repeat (10 times) {
  checkForUpdates()
}

repeat (10 times) { (index, last) =>
  checkForUpdates(index, last)
}
