// Exercise 5: Traits as Mixins
// We discussed mixins for a Queue class. This "exercise" is more
// of a demonstration of what's possible, to save class time.
// Experiment with ordering the traits differently. Do the results
// make sense to you?

// Here's the base trait that defines the Queue abstraction:
trait Queue[T] {
  def get(): T
  def put(t: T)   
}

// The mixin that adds loggings:
trait QueueLogging[T] 
 extends Queue[T] {
  abstract override def put(t: T) = {  
   println("put("+t+")")  
   super.put(t) 
  }
}

// The mixin that adds filtering:
trait QueueFiltering[T] 
 extends Queue[T] {
  abstract override def put(t: T) = { 
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

// Construct an object, adding the traits "on the fly":
val sq = new StandardQueue[Int]  
     with QueueFiltering[Int]  
     with QueueLogging[Int] {
  def veto(t: Int) = t < 0
}

// NOTE: Our first example of a "for comprehension".
// Think of it as a normal for loop for now. 
// Note that we can express the range -2, -1, 0, 1, 2
// using "-2 to 2".
println("\nfiltering and logging")
for (i <- -2 to 2) {  
    sq.put(i)  
}  
// Use our "getAb" method show we can print the list contents using foreach
println("The contents of the list:")
sq.getAb foreach println

// Here is the new double-putting mixin.
trait QueueDoubling[T] 
 extends Queue[T] {
  abstract override def put(t: T) = { 
      super.put(t)
      super.put(t)
    }
  }

// Mix it in LAST:
val sq2 = new StandardQueue[Int]  
    with QueueFiltering[Int]  
    with QueueLogging[Int]   
    with QueueDoubling[Int]   { 
  def veto(t: Int) = t < 0
}

println("\nfiltering, logging, doubling")
for (i <- -2 to 2) {  
  sq2.put(i)  
}  
println("The contents of the list:")
sq2.getAb foreach println

// Use it FIRST:
val sq3 = new StandardQueue[Int]  
    with QueueDoubling[Int]
    with QueueFiltering[Int]  
    with QueueLogging[Int]   { 
  def veto(t: Int) = t < 0
}

println("\ndoubling, filtering, logging")
for (i <- -2 to 2) {  
    sq3.put(i)  
}  
println("The contents of the list:")
sq3.getAb foreach println

