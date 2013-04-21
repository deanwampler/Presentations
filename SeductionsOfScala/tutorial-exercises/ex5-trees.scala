// Exercise 5: Tree Abstract Data Type
//
// We've already seen lists, which actually are implemented very much like
// option, where there is a base class List and two possible subclasses,
// Nil for the empty list and :: (yes, that's the actual class name!) for the
// non-empty list.
// Let's implement a similar Tree type, where there  are two possible concrete
// subclasses, 

sealed abstract class Tree[+A] 

case class T[+A](left: Tree[A], right: Tree[A]) extends Tree[A] 

case class Leaf[+A](data: A) extends Tree[A] 

val tree = 
    new T(
        new T(
            new T(
                new Leaf(1),
                new Leaf(2)),
            new Leaf(3)),
        new T(
            new Leaf(4),
            new Leaf(5)))

import CheapTests._

tree.toString is "T(T(T(Leaf(1),Leaf(2)),Leaf(3)),T(Leaf(4),Leaf(5)))"

// Add a foreach method to this hierarchy. The signature is:
//  def foreach[B](f: A => B): Unit
// where foreach iterates through the collection, applying f to each element.
// Uncomment the following code and make the test pass.
// val sb = new StringBuffer
// tree.foreach(sb.append(_).append("|"))
// sb.toString is "1|2|3|4|5|"

// Add a fold method with the following signature and make the test pass.
//  def fold[Accum](seed: Accum)(f: (Accum,A) => Accum): Accum
// Note that the 1st argument to the function is the "seed", the initial value
// for the accumulator. The 2nd argument LIST is the function applied to each
// element. It takes as its 1st argument the current value for the  accumulator
// you are folding "on to". The function supplied by the user must return the 
// current value for this accumulator.
// val sb2 = tree.fold(new StringBuffer)((buff, x) => buff.append(x).append("-"))
// sb2.toString is "1-2-3-4-5-"

// Extra: Try implementing fold with foreach and vice-versa.

println("Success!")
