// Hygienic Macros
// New to Scala 2.10 (SIP-16)
// Compile this file separately:
//   scalac macros-Print-sip16.scala
// Then look at macros-sip16.scala.
//
// See macros-sip16.scala for more discussion.
//
// You can only use the dynamic feature if you start scala with
// the language feature enabled:
//   scala -language:experimental.macros ...
// OR, you import the following:

import scala.language.experimental.macros

import scala.reflect.macros.Context
import collection.mutable.ListBuffer
import collection.mutable.Stack

object Print {
  // macro definition is a normal function with anything you fancy in its signature
  // its body, though, is nothing more that a reference to an implementation
  def printf(format: String, params: Any*): Unit = macro printf_impl

  // macro implementation must correspond to macro definitions that use it
  // required signature is quite involved, but don't be scared
  // if the compiler is unhappy, it will print the signature it wants in the error message
  def printf_impl(c: Context)(format: c.Expr[String], params: c.Expr[Any]*): c.Expr[Unit] = {
    // compiler API is exposed in scala.reflect.macros.Context
    // its most important part, reflection API, is accessible via c.universe
    // it's customary to import c.universe._, because it includes a lot of routinely used stuff
    import c.universe._

    // first of all, we parse the provided format string
    // macros run during the compile-time, so they operate on trees, not on values
    // this means that the format parameter of our macro will be a compile-time literal
    // not an object of type java.lang.String
    // this also means that the code below won't work for printf(get_format(), ...)
    // because in that case format won't be a string literal
    // but rather an AST that represents addition of two string literals
    // adjusting the macro to work for arbitrary stuff is left as an exercise for the reader
    val Literal(Constant(s_format: String)) = format.tree

    // here we jump straight into the compiler
    // the paragraph below creates temporary vals that precompute expressions being formatted
    // to learn more about dynamic generation of Scala code, take a look at our slides:
    // http://scalamacros.org/talks/2012-04-28-MetaprogrammingInScala210.pdf
    val evals = ListBuffer[ValDef]()
    def precompute(value: Tree, tpe: Type): Ident = {
      val freshName = newTermName(c.fresh("eval$"))
      evals += ValDef(Modifiers(), freshName, TypeTree(tpe), value)
      Ident(freshName)
    }

    // nothing fancy here, just bread and butter AST manipulations
    // extract trees from parameters of a macro, decompose/analyze and transform them
    // note how we get a hold of Scala types that correspond to Int and String
    // this works for a small set of core types
    // but in most cases you will have to create types by yourself
    // read up the aforementioned slides to learn more about types
    val paramsStack = Stack[Tree]((params map (_.tree)): _*)
    val refs = s_format.split("(?<=%[\\w%])|(?=%[\\w%])") map {
      case "%d" => precompute(paramsStack.pop, typeOf[Int])
      case "%s" => precompute(paramsStack.pop, typeOf[String])
      case "%%" => Literal(Constant("%"))
      case part => Literal(Constant(part))
    }

    // now we combine all the code we have generated into a Block
    // note the call to reify, which provides a shortcut for creating ASTs
    // learn more about reify in our documentation
    val stats = evals ++ refs.map(ref => reify(print(c.Expr[Any](ref).splice)).tree)
    c.Expr[Unit](Block(stats.toList, Literal(Constant(()))))
  }
}
