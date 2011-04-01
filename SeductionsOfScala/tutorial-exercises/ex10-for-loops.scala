// Exercise 10: For Loops
// Generate a list of 3-tuples that satisfy these properties:
//   1) i >= j >= k 
//   2) (i+j+k) % 3 == 0
//
// One solution is the following, which simply prints the tuples as found:
for { 
  ??
}
  format(“(%d,%d,%d) “, i, j, k)

// Another solution generates a list using "yield", then you can print the 
// list afterwards.
val list = for { 
  ??
} 
  yield ((i, j, k)) 
  
println(list)
