// Exercise 3: Implicit Conversations
// Write an implicit method to convert a 3-element List to a Tuple3.
// Use it to successfully invoke:
// process(List(4,5,6))
// Hint:
// list(n) returns the nth element (0-based).
// You can run this file with "scala ex3-implicit-conversions.scala"

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
