import loop.Loop._
import java.io._

object Main {
  def main(args:Array[String]) = {
    println("Main:")
    loop (new File(args(0))) { 
      (n, line) => println(format("%3d: %s", n, line))
    }
  }
}