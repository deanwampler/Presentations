// Exercise 3: Mapper
// Experiment with calling "mapper" using different lists and functions.
// Experiment with the implementation of mapper. For example, how would you
// implement a filter?
// You can run this file with "scala ex3-mapper.scala"

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
