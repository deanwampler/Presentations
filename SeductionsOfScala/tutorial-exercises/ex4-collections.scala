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

val expected1 = list
expected1 is List("time", "the", "is", "now")

val expected2 = list
expected2 is List("NOW", "IS", "THE", "TIME")

// Return the first element
val expected3 = list
expected3 is "now"

// Return the tail, all but the first element
val expected4 = list
expected4 is List("is", "the", "time")

// Return the last element. (Warning: O(n) time)
val expected5 = list
expected5 is "time"

// Sort the elements by length
val expected6 = list
expected6 is List("is", "now", "the", "time")

// "Partition" the collection elements into two collections,
// where one has the words that start with "t" and the other
// collection has the rest of the words. 
val expected7 = list
expected7 is Tuple2(List("the", "time"),List("now", "is"))

// "Zip" the collection elements with their lengths. 
val expected8 = list
expected8 is List(("now", 3), ("is", 2), ("the", 3), ("time", 4))

// Make a string from the list that matches the expected
// string shown
val expected9 = list
expected9 is "[now-is-the-time]"

// Map the names map to a 3-element list where the 3 names are "first last".
// The following anonymous function can be written several ways. Note that
// the anonymous function expects a Tuple2 argument for the key and value:
val expected10  = names 
expected10 is List("Martin Odersky", "Joe Armstrong", "Simon Peyton Jones")

// If you know SQL, you know the GROUP BY operation. Do the same thing using
// a single Map API call to group by the numbers used as keys.
val stuff = Map(1 -> "a", 2 -> "b", 1 -> "c", 3 -> "d", 2 -> "e")
val expected11 = stuff 
expected11 is Map(
  1 -> Map(1 -> "a", 1 -> "c"), 
  2 -> Map(2 -> "b", 2 -> "e"), 
  3 -> Map(3 -> "d") 
)

// To anticipate the next section, note what happens when we retrieve
// map elements:

val expected12 = expected11
expected12 is Some(Map(1 -> "a", 1 -> "c"))
val expected13 = expected11
expected13 is None
val expected14 = expected11
expected14 is Map(4 -> "unknown")

// Final example:

import scala.collection.immutable.TreeSet

// Extract all the unique characters (respecting case) into a list:
object Uniq {
  def main(args: Array[String]) = 
    args.toList  // convert to list
      .flatMap(_.toList)  // flatten the list of strings to single list of characters
      .sort(_ < _)        // sort the list alphanumerically
      .foldLeft(new TreeSet[Char])((set,s) => set + s)  // "fold" the list elements into a set
      .toList             // convert to the final list
}

println("Success!")
