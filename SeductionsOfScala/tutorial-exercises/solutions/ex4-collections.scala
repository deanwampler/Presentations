// Exercise 4: Collections and operations.
// For this exercise, you'll want to open up the Scaladocs
// for the immutable List and Map types.
//
// NOTE: For a list of fun problems to practice these 
// collection transformations, see http://projecteuler.net/.


import CheapTests._

// Here are collections we'll use:
val list = List("now", "is", "the", "time")
val names  = Map(
  "Martin" -> "Odersky",
  "Joe" -> "Armstrong",
  "Simon" -> "Peyton Jones"
)

// To make the tests pass, replace the definitions of
//   val expectedN = list
// etc. with the correct calls to one (or more!) List or
// Map methods. For example, the first one should be
//   val expected1 = list.reverse
// (Do as many as you want.)

val expected1 = list.reverse
expected1 is List("time", "the", "is", "now")

// Same things:
// val expected2 = list.map (_.toUpperCase)
// val expected2 = list map (_.toUpperCase)
// val expected2 = list map {_.toUpperCase}
val expected2 = list map (_.toUpperCase)
expected2 is List("NOW", "IS", "THE", "TIME")

// Return the first element
val expected3 = list.head
expected3 is "now"

// Return the tail, all but the first element
val expected4 = list.tail
expected4 is List("is", "the", "time")

// Return the last element. (Warning: O(n) time)
val expected5 = list.last
expected5 is "time"

// Sort the elements by length
val expected6 = list sortBy (_.length)
expected6 is List("is", "now", "the", "time")

// "Partition" the collection elements into two collections,
// where one has the words that start with "t" and the other
// collection has the rest of the words. 
val expected7 = list partition (_(0) == 't')
expected7 is Tuple2(List("the", "time"),List("now", "is"))

// "Zip" the collection elements with their lengths. 
val expected8 = list zip (list map (_.length))
expected8 is List(("now", 3), ("is", 2), ("the", 3), ("time", 4))

// Make a string from the list that matches the expected
// string shown
val expected9 = list mkString ("[", "-", "]")
expected9 is "[now-is-the-time]"

// Map the "names" above to a 3-element list where each name is "first last".
// The anonymous function passed to map() can be written several ways. Note that
// the anonymous function expects a Tuple2 argument for the key and value, OR
// it can also be written as PartialFunction, which we'll discuss later:

// Note the unfortunate requirement that one of these forms require
// {} to wrap the anon. function, not ()! 
// The "case" example is using Scala's pattern matching, which we'll explore
// shortly.
val expected10a = names map ( key_value => key_value._1+" "+key_value._2 )
val expected10  = names map { case (key, value) => s"$key $value" }
expected10 is List("Martin Odersky", "Joe Armstrong", "Simon Peyton Jones")

// If you know SQL, you know the GROUP BY operation. Do the same thing using
// a single Map API call to group by the numbers used as keys.
val stuff: Map[Int,String] = Map(1 -> "a", 2 -> "b", 1 -> "c", 3 -> "d", 2 -> "e")
val expected11 = stuff groupBy { case (key, value) => key }
expected11 is Map(
  1 -> Map(1 -> "a", 1 -> "c"), 
  2 -> Map(2 -> "b", 2 -> "e"), 
  3 -> Map(3 -> "d") 
)

// To anticipate the next section, note what happens when we retrieve
// map elements:

val expected12 = expected11.get(1)
expected12 is Some(Map(1 -> "a", 1 -> "c"))
val expected13 = expected11.get(4)
expected13 is None
val expected14 = expected11.getOrElse(4, Map(4 -> "unknown"))
expected14 is Map(4 -> "unknown")

// Final example:

import scala.collection.immutable.TreeSet

// Extract all the unique characters (respecting case) into a list.
// You could also just return the TreeSet, which is a Red-Black tree.
// Note the three function signatures. I'll explain why in class...
def uniques(arg: String): List[Char] = uniques(List(arg))
def uniques(arg: String, args: String*): List[Char] = uniques(arg +: (args.toSeq))
def uniques(args: Seq[String]): List[Char] = 
  args.flatMap(_.toList) // Flatten the sequence of strings to single list of chars.
      .sortWith(_ < _)   // Sort alphanumerically then "fold" the elements into a set
      .foldLeft(new TreeSet[Char])((set,s) => set + s)
      .toList            // convert to the final list

val expected15 = List('N', 'T', 'e', 'h', 'i', 'm', 'o', 's', 't', 'w')
uniques("Now", "is", "the", "Time") is expected15
uniques("Now" :: "is" :: "the" :: "Time" :: Nil) is expected15
uniques(List("Now", "is", "the", "Time")) is expected15
uniques("Now") is List('N', 'o', 'w')

println("Success!")
