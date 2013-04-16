// Exercise 9: For Loops

import CheapTests._

// Generate a list of 3-tuples that satisfy these properties:
//   1) i >= j >= k 
//   2) (i+j+k) % 3 == 0
// We'll ceate a list of tuples with the i,j,k elements.

val actual1 = for { 
  i <- 1 to 10  // arbitrary upper limit
  j <- 1 to i
  k <- 1 to j
  if (i + j + k) % 3 == 0
}   yield ((i, j, k)) 

// Note that a Vector should be created:  
actual1 is Vector(
  (1,1,1), 
  (2,2,2), 
  (3,2,1), (3,3,3), 
  (4,1,1), (4,3,2), (4,4,1), (4,4,4), 
  (5,2,2), (5,3,1), (5,4,3), (5,5,2), (5,5,5), 
  (6,2,1), (6,3,3), (6,4,2), (6,5,1), (6,5,4), (6,6,3), (6,6,6), 
  (7,1,1), (7,3,2), (7,4,1), (7,4,4), (7,5,3), (7,6,2), (7,6,5), (7,7,1), (7,7,4), (7,7,7), 
  (8,2,2), (8,3,1), (8,4,3), (8,5,2), (8,5,5), (8,6,1), (8,6,4), (8,7,3), (8,7,6), (8,8,2), (8,8,5), (8,8,8), 
  (9,2,1), (9,3,3), (9,4,2), (9,5,1), (9,5,4), (9,6,3), (9,6,6), (9,7,2), (9,7,5), (9,8,1), (9,8,4), (9,8,7), (9,9,3), (9,9,6), (9,9,9), 
  (10,1,1), (10,3,2), (10,4,1), (10,4,4), (10,5,3), (10,6,2), (10,6,5), (10,7,1), (10,7,4), (10,7,7), (10,8,3), (10,8,6), (10,9,2), (10,9,5), (10,9,8), (10,10,1), (10,10,4), (10,10,7), (10,10,10))

// Iterate through a collection of Options and observe the "Monadic" behavior:

val list = List(Option(1), None, None, Option(2), Option(3), None, None, Option(4), None)
val actual2 = (for {
  option <- list
  element <- option
  } yield element)

actual2 is List(1,2,3,4)

println("Success!")