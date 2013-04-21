// Type comparison operator
// New to Scala 2.10

import scala.reflect.runtime.universe._

def meth[A: TypeTag](a: List[A]) = typeOf[A] match {
    case t if t =:= typeOf[String] => "list of strings"
    case t if t =:= typeOf[Int] => "list of ints"
    case t => "error: " + t
}
println(meth(List(2)))
println(meth(List("a")))
println(meth(List("a", 2)))
