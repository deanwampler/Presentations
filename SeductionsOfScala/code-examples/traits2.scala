trait Queue[T] {
  def get(): T  // Use parentheses to indicate state modifying
  def put(t: T)  
  def size: Int // Omit parentheses to indicate state preserving
}

trait QueueLogging[T] 
 extends Queue[T] {
  abstract override def put(
    t: T) = {  
   println("put("+t+")")  
   super.put(t) 
  }
  abstract override def get(): T = {
   val t = super.get() 
   println("get("+t+")")  
   t
  }
}

trait QueueFiltering[T] 
 extends Queue[T] {
  abstract override def put(
    t: T) = { 
   if (veto(t)) 
     println(t+" rejected!")
   else  
     super.put(t) 
  }
  def veto(t: T): Boolean
}

class StandardQueue[T] 
 extends Queue[T] {  
  import scala.collection.mutable.ArrayBuffer  
  private val ab =
        new ArrayBuffer[T]  
  def put(t: T) = ab += t  
  def get() = ab.remove(0)
  def size = ab.size 
}
  
val sq = new StandardQueue[Int]  
     with QueueLogging[Int]  
     with QueueFiltering[Int] { 
  def veto(t: Int) = t < 0
}

println("logging declared before filtering")
for (i <- -2 to 2) {  
    sq.put(i)  
}  
for (i <- 1 to sq.size) {  
    sq.get()  
}  

// Create a parameterized class that mixes traits.
class AllQueue[T] extends StandardQueue[T]  
     with QueueFiltering[T]  
     with QueueLogging[T]   { 
  def veto(t: T) = false
}
val aq = new AllQueue[Int]
println("\nAllQueue")
for (i <- -2 to 2) {  
    aq.put(i)  
}  
for (i <- 1 to aq.size) {  
    aq.get()  
}  

// Create an instance that mixes traits on the fly.
val sq2 = new StandardQueue[Int]  
     with QueueFiltering[Int]  
     with QueueLogging[Int]   { 
  def veto(t: Int) = t < 0
}

println("\nfiltering declared before logging")
for (i <- -2 to 2) {  
    sq2.put(i)  
}
for (i <- 1 to sq2.size) {  
    sq2.get()  
}  

trait QueueDoubling 
 extends Queue[Int] {
  abstract override def put(
    t: Int) = { 
      super.put(2 * t)
    }
  }

val sq3 = new StandardQueue[Int]  
     with QueueFiltering[Int]  
     with QueueLogging[Int]   
     with QueueDoubling   { 
  def veto(t: Int) = t < 0
}

println("\nfiltering, logging, doubling")
for (i <- -2 to 2) {  
    sq3.put(i)  
}  
for (i <- 1 to sq3.size) {  
    sq3.get()  
}  

val sq4 = new StandardQueue[Int]  
      with QueueDoubling
     with QueueFiltering[Int]  
     with QueueLogging[Int]   { 
  def veto(t: Int) = t < 0
}

println("\nfiltering, logging, doubling")
for (i <- -2 to 2) {  
    sq4.put(i)  
}
for (i <- 1 to sq4.size) {  
    sq4.get()  
}  

