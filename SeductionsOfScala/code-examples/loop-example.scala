import loop.Loop._
import java.io._

loop (new File("loop.scala")) { 
  (n, line) =>
  printf("%3d: %s\n", n, line)
}