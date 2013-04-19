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
trait QueueLogging[T] extends Queue[T] { 
  abstract override def put(t: T) = {  
    writeln("put("+t+")")  
    super.put(t) 
  }
  def writeln(message: String): Unit
}

// A logging trait that writes to stdout:
trait StdoutQueueLogging[T] extends QueueLogging[T] {
  def writeln(message: String) = { Console.out.println(message) }
}

// A logging trait that accumulates messages in a string:
trait StringQueueLogging[T] extends QueueLogging[T] {
  protected val buff = new StringBuilder
  def messages = buff.toString
  def writeln(message: String) = { buff.append(message).append("\n") }
}

// The mixin that adds filtering:
trait QueueFiltering[T] extends Queue[T] {
  abstract override def put(t: T) = { 
   if (! veto(t)) 
     super.put(t) 
  }
  def veto(t: T): Boolean
}

// A generic queue implementation:
class StandardQueue[T] extends Queue[T] {  
  import scala.collection.mutable.ArrayBuffer  
  private val ab = new ArrayBuffer[T]  
  def put(t: T) = ab += t  
  def get() = ab.remove(0) 
  // Add foreach for traversing the collection.
  def foreach (f: T => Unit) = ab foreach f
  // Add toList to make it easy to examine the queue contents.
  def toList: List[T] = ab.toList
}

// Construct an object, adding the traits "on the fly":
val sq1 = new StandardQueue[Int]  
    with QueueFiltering[Int]  
    with StringQueueLogging[Int] { 
  def veto(t: Int) = t < 0
}

import CheapTests._

// NOTE: Our first example of a "for comprehension".
// Think of it as a normal for loop for now. 
// Note that we can express the range -2, -1, 0, 1, 2
// using "-2 to 2".
println("\nfiltering and logging")
for (i <- -2 to 2) {  
  sq1.put(i)  
}  

println("The contents of the queue should be 012:")
sq1 foreach print
println

sq1.toList is List(0, 1, 2)
sq1.messages is """put(-2)
put(-1)
put(0)
put(1)
put(2)
"""

// Here is the new double-putting mixin.
trait QueueDoubling[T] extends Queue[T] {
  abstract override def put(t: T) = { 
    super.put(t)
    super.put(t)
  }
}

// Mix in QueueDoubling LAST:
val sq2 = new StandardQueue[Int]  
    with QueueFiltering[Int]  
    with StringQueueLogging[Int]   
    with QueueDoubling[Int] { 
  def veto(t: Int) = t < 0
}

println("\nfiltering, logging, doubling")
for (i <- -2 to 2) {  
  sq2.put(i)  
}  
println("The contents of the queue should be 001122:")
sq2 foreach print
println

sq2.messages is """put(-2)
put(-2)
put(-1)
put(-1)
put(0)
put(0)
put(1)
put(1)
put(2)
put(2)
"""

// Mix in QueueDoubling FIRST.
// Does it change the queue contents? Does it change the logged messages?
val sq3 = new StandardQueue[Int]  
    with QueueDoubling[Int]
    with QueueFiltering[Int]  
    with StringQueueLogging[Int] { 
  def veto(t: Int) = t < 0
}

println("\ndoubling, filtering, logging")
for (i <- -2 to 2) {  
  sq3.put(i)  
}  
println("The contents of the queue should be 001122:")
sq3 foreach print
println

// Fill in the correct expected log messages:
sq3.messages is """put(-2)
put(-1)
put(0)
put(1)
put(2)
"""

// For the last example, replace StringQueueLogging with StdoutQueueLogging.
// Note the output written to the console:

val sq4 = new StandardQueue[Int]  
    with QueueDoubling[Int]
    with QueueFiltering[Int]  
    with StdoutQueueLogging[Int] { 
  def veto(t: Int) = t < 0
}
println("\ndoubling, filtering, logging (to stdout)")
for (i <- -2 to 2) {  
  sq4.put(i)  
}  
println
println("The contents of the queue should be 001122:")
sq4 foreach print
println

// Add a nice toString message to StandardQueue, so the foreach is 
// no longer required:

// A generic queue implementation, with a toString!
class StandardQueue2[T] extends Queue[T] {  
  import scala.collection.mutable.ArrayBuffer  
  private val ab = new ArrayBuffer[T]  
  def put(t: T) = ab += t  
  def get() = ab.remove(0) 
  // Add foreach for traversing the collection.
  def foreach (f: T => Unit) = ab foreach f
  // Add toList to make it easy to examine the queue contents.
  def toList: List[T] = ab.toList

  // One of many possible approaches:
  override def toString = 
    ab.mkString("Queue(", ",", ")")
}

val sq5 = new StandardQueue2[Int]  
    with QueueDoubling[Int]
    with QueueFiltering[Int]  
    with StringQueueLogging[Int] { 
  def veto(t: Int) = t < 0
}

println("\ndoubling, filtering, logging (with toString)")
for (i <- -2 to 2) {  
  sq5.put(i)  
}  
println(sq5)
