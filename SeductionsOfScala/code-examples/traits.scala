trait Queue[T] {
  def get(): T
  def put(t: T)   
}

trait QueueLogging[T] 
 extends Queue[T] {
  abstract override def put(
    t: T) = {  
   println("put("+t+")")  
   super.put(t) 
  }
}

class StandardQueue[T] 
          extends Queue[T] {  
  import scala.collection.mutable.ArrayBuffer  
  private val ab =
        new ArrayBuffer[T]  
  def put(t: T) = ab += t  
  def get() = ab.remove(0) 
}
  
val sq = new StandardQueue[Int]  
        with QueueLogging[Int]  
  
sq.put(10)        // #1  
println(sq.get()) // #2  
// => put(10)     (on #1)  
// => 10          (on #2)