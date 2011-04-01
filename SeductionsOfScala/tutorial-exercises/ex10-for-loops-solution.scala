// Exercise 10: For Loops
// Generate a list of 3-tuples that satisfy these properties:
//   1) i >= j >= k 
//   2) (i+j+k) % 3 == 0
// You can run this file with "scala ex10-for-loops-solution.scala"
//
// One solution is the following, which simply prints the tuples as found:
for { 
  i <- 1 to 10  // arbitrary upper limit
  j <- 1 to i
  k <- 1 to j
  if (i + j + k) % 3 == 0
}
  format("(%d,%d,%d) ", i, j, k)
println("\n")

// Another solution generates a list using "yield", then you can print the 
// list afterwards.
val list = for { 
  i <- 1 to 10  // arbitrary upper limit
  j <- 1 to i
  k <- 1 to j
  if (i + j + k) % 3 == 0
} 
  yield ((i, j, k)) 
  
println(list)
