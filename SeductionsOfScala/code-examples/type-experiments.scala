// Experiment: Higher-kinded types with structural type bounds.
trait F[M[_] <: { def size:Int }] {
    var x : M[Int]
    def m(i: Int) = if (x.size == 0) i else x.size
}

// Experiment: Higher-kinded types and subclassing with a custom CanBuildFrom.

import scala.language.higherKinds
import scala.collection.generic.CanBuildFrom
import scala.collection.mutable.Builder

trait F[X, M[X] <: IndexedSeq[X]] {
    val xs : M[X]
    def :+[That](x:X)(implicit bf: CanBuildFrom[F[X,M], X, That]): That = {
        val builder = bf(this)
        builder += x
        builder.result
    }
}

case class C(xs:Vector[Int] = Vector.empty) extends F[Int, Vector]

object C {
    class CBuilder(var c: C) extends Builder[Int, C] {
        def +=(elem: Int): CBuilder.this.type = {
            c = c.copy(xs = c.xs :+ elem)
            this
        }
        def clear():Unit = new C()
        def result():C = c
    }
    class CCBF extends CanBuildFrom[F[Int,Vector], Int, C] {
        def apply() = new CBuilder(new C)
        def apply(c: F[Int,Vector]) = new CBuilder(new C(c.xs))
    }
    implicit val bf = new CCBF
}

import C.bf

(1 to 5).foldLeft(C()){ (c, i) => c :+ i }

// Experiment: This avoids subclassing and requiring a custom CanBuildFrom

import scala.language.higherKinds
import scala.collection.mutable.Builder

case class F3[X, M[X] <: IndexedSeq[X]](
    xs: M[X])(implicit cbfM: CanBuildFrom[M[X], X, M[X]]) {
    def :+ (x:X) : F3[X,M] = {
        val builder = cbfM()
        builder ++= xs :+ x
        F3(builder.result)
    } 
}
