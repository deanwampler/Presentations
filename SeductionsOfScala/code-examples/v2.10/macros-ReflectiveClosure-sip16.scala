package examples.macros

import language.experimental.macros
import language.implicitConversions
import scala.reflect.macros.Context
import scala.reflect.runtime.universe.Tree

// https://gist.github.com/4542402
// Compile with scalac.

class ReflectiveClosure[A, B](val tree: Tree, fn: Function1[A, B]) extends Function1[A, B] {
  def apply(x: A) = fn(x)
}
 
object ReflectiveClosure {
  implicit def reflectClosure[A, B](f: Function1[A,B]): ReflectiveClosure[A, B] = macro Macros.reflectiveClosureImpl[A, B]
}
 
object Macros {
  def reflectiveClosureImpl[A, B](c: Context)(f: c.Expr[Function1[A, B]]) = {
    import c.universe._
    val u = treeBuild.mkRuntimeUniverseRef
    val m = EmptyTree
    val tree = c.Expr[scala.reflect.runtime.universe.Tree](Select(c.reifyTree(u, m, f.tree), newTermName("tree")))
    c.universe.reify(new ReflectiveClosure(tree.splice, f.splice))
  }
}
