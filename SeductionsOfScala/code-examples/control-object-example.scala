import java.io._

// Print the input with line numbers.
object Numberator {
  def loop(file: File) (f: (Int, String) => Unit) = {
    val reader = new BufferedReader(new FileReader(file))
    def doLoop(lineNumber: Int): Unit = {
      val line = reader.readLine()
      if (line != null) {
        f(lineNumber, line)
        doLoop(lineNumber+1)
      }
    }
    doLoop(1)
  }
}

import Numberator.loop

loop (new File("control-object-example.scala")) { (n, line) =>
  format("%3d: %s\n", n, line)
}