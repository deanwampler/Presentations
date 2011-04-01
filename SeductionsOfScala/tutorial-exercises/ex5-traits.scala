// Exercise 5: Traits as Mixins
// We discussed mixins for a Queue class. Define a QueueMultiplier trait that
// puts the new element twice.
// Use it with the other traits we defined in different orderings. 
// Do the results make sense to you?
// You can run this file with "scala ex5-traits.scala"

// Here's the base trait that defines the Queue abstraction:
trait Queue[T] {
  def get(): T
  def put(t: T)   
}

// The mixin that adds loggings:
trait QueueLogging[T] 
 extends Queue[T] {
  abstract override def put(
    t: T) = {  
   println("put("+t+")")  
   super.put(t) 
  }
}

// The mixin that adds filtering:
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

// The generic queue implementation:
class StandardQueue[T] 
          extends Queue[T] {  
  import scala.collection.mutable.ArrayBuffer  
  private val ab =
        new ArrayBuffer[T]  
  def put(t: T) = ab += t  
  def get() = ab.remove(0) 
  // Add a method to get the buffer we're using (see below).
  def getAb = ab
}

// Here's the first example we showed:
val sq = new StandardQueue[Int]  
     with QueueFiltering[Int]  
     with QueueLogging[Int] {
  def veto(t: Int) = t < 0
}

println("\nfiltering and logging")
for (i <- -2 to 2) {  
    sq.put(i)  
}  
println("The contents of the list:")
// Use our "getAb" method show we can print the list contents using foreach
sq.getAb foreach println
