// Exercise 3: Mapper
// Experiment with calling "mapper" using different lists and functions.
// Experiment with the implementation of mapper. For example, how would you
// implement a filter?
// You can run this file with "scala ex3-mapper-solution.scala"

// Here is our first implementation:
def mapper(l: List[AnyRef], f: (AnyRef) => Any) = {
  l.map(f(_))
} 

// Here is one example of usage:
println(mapper("a" :: "b" :: Nil, 
 (x:AnyRef) => x.getClass))

// Here is the improved implementation using a parameterized type:
def mapper2[T](l: List[T], f: (T) => Any) = {
  l.map(f(_))
} 

// And in use:
println(mapper2("a" :: "b" :: Nil, 
 (x:String) => x.toUpperCase))

// We'll just use the better mapper2 implementation.

// Let's create a type with an inherited method.

trait Root {
  def modify(i:Int): Int
}
class A extends Root {
  def modify(i:Int) = 2 * i  
}
class B extends Root {
  def modify(i:Int) = 3 * i  
}
class C extends Root {
  def modify(i:Int) = 4 * i  
}

// We'll use the other way to create a list of "Root" objects:
println(mapper2(List(new A, new B, new C),
  (r:Root) => r.modify(2)))
// => List(4, 6, 8)

// Implement a filter? Here's a not-very-good implementation, because it uses
// a mutable variable:

var result = List[Int]()   // empty list
val listMaker = { r:Root => 
  val i = r.modify(2)
  if (i > 4) 
    result ::= i
}

mapper2(List(new A, new B, new C), listMaker)
println(result)
// => List(8, 6)  // note that the new list is reversed.

