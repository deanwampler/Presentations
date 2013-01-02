// Exercise 7: Types Demonstration
// We'll demonstate a few things.

import CheapTests._

// Mapping the elements of one type to another type is straightforward.
List(1, 2, 3) map (_.toString) is List("1", "2", "3")

// What happens if we want to add an element to a list, but the element
// is the same type of the existing list elements? Note the type of
// "actual" returned by the REPL:
val actual = "four" :: List(1, 2, 3)
actual is List("four", 1, 2, 3)

println("Success!")
