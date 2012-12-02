// The Reflection API has been enhanced. 
// New to Scala 2.10.
// Manifest and ClassManifest are deprecated in favor of the newer,
// more full featured TypeTag and ClassTag.
// This example adapted from the TypeTag scaladoc:
// file://localhost/Applications/Development/scala-docs-2.10.0-RC3/scala/reflect/api/TypeTags.html

import scala.reflect.runtime.universe._

// Returns a list of the types of the arguments:

def paramInfo[T](x: T)(implicit tag: TypeTag[T]): Unit = {
  val targs = tag.tpe match { case TypeRef(_, _, args) => args }
  println(s"type of $x has type arguments $targs")
}

paramInfo(42)
// returns: type of 42 has type arguments List()
// I'm not sure why the answer isn't List(Int), because...

paramInfo(List(1, 2))
// returns: type of List(1, 2) has type arguments List(Int)

paramInfo(42, 3.14159, Option("now is the time"))
// returns: type of (42,3.14159,Some(now is the time)) has type arguments List(Int, Double, scala.Option[String])

// The TypeRef fields extracted are:
//   1. The package type.
//   2. The erased type of x, where x is a single argument, so for argument lists
//      like (1,2,3), the type is actually Tuple3.
//   3. The list of unerased types for each argument.
def allInfo[T](x: T)(implicit tag: TypeTag[T]): Unit = {
  val all = tag.tpe match { case TypeRef(x, y, args) => (x,y,args) }
  println(s"all TypeTag fields for $x is $all")
}

allInfo(42)
// returns: all TypeTag fields for 42 is (scala.type,class Int,List())

allInfo(List(1, 2))
// returns: all TypeTag fields for List(1, 2) is (scala.collection.immutable.type,class List,List(Int))

allInfo(42, 3.14159, Option("now is the time"))
// returns: all TypeTag fields for (42,3.14159,Some(now is the time)) is (scala.type,class Tuple3,List(Int, Double, scala.Option[String]))

// ClassTag: Contains less information, as described on its Scaladocs page:
//   ClassTags are a weaker special case of scala.reflect.api.TypeTags#TypeTags, 
//   in that they wrap only the runtime class of a given type, whereas a TypeTag
//   contains all static type information. That is, ClassTags are constructed from
//   knowing only the top-level class of a type, without necessarily knowing all 
//   of its argument types. This runtime information is enough for runtime Array
//   creation.

import scala.reflect.ClassTag

// The _* type says, "keep elems as a variable-length list of values, not a single sequence."
def mkArray[T : ClassTag](elems: T*) = Array[T](elems: _*)
// prints: mkArray: [T](elems: T*)(implicit evidence$1: scala.reflect.ClassTag[T])Array[T]

mkArray(42, 13)
// prints: Array[Int] = Array(42, 13)

mkArray(42, 3.14159)
// prints: Array[Double] = Array(42.0, 3.14159)  
// interesting: not Array[AnyVal] = Array(42, 3.14159)

mkArray("Japan","Brazil","Germany")
// prints: Array[String] = Array(Japan, Brazil, Germany)

// Note that the way mkArray is declared with [T : ClassTag] 
// is short hand for the following.

def mkArray1[T](elems: T*)(implicit ct: ClassTag[T]) = Array[T](elems: _*)
// prints: mkArray1: [T](elems: T*)(implicit ct: scala.reflect.ClassTag[T])Array[T]

mkArray1(42, 13)
mkArray1(42, 3.14159)
mkArray1("Japan","Brazil","Germany")
