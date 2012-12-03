// Exercise 4: Implicit Conversations
// Write an implicit method to convert a 3-element List to a Tuple3.
// Use it to successfully invoke:
// process(List(4,5,6))
// Hint:
// list(n) returns the nth element (0-based).
// You can run this file with "scala ex4-implicit-conversions-solution.scala"

// Here's the definition of "process":
def process[A,B,C](t: Tuple3[A,B,C]) = {
  println(t._1) 
  println(t._2) 
  println(t._3)
} 
// Recall there are two ways to define a 3-tuple:
process((1,2,3))
process(new Tuple3(1,2,3))
//process(List(4,5,6)) // fails

// For our script, it would be sufficient to define an implicit conversion 
// method here and just use it. For completeness, let's do more realistic 
// implementation. You would normally put these methods in an object, then
// import that method into the scope where we need it.

object List2TupleConverter {
  // We'll assume that the input list always has 3 elements:
  implicit def list2Tuple[T](list: List[T]) = (list(0), list(1), list(2))
}

import List2TupleConverter._

process(List(4,5,6)) 
