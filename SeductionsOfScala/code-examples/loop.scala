package loop
import java.io._

object Loop {
  def loop(file: File)(f: (Int,String) => Unit) = {
    val reader = new BufferedReader(new FileReader(file))
    def doLoop(n: Int):Unit = {
     val l = reader.readLine()
     if (l != null) {
      f(n, l)
      doLoop(n+1)
     }
    }    
    doLoop(1)
  }
} 
