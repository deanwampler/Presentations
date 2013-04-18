// Note that we aren't checking for negative arguments!

// This is a right-recursion implementation.
//
def factr(i: Int): Long = 
  if (i == 1) 1L 
  else i*factr(i-1)

// This is a left-recursion implementation.
def factl(i: Int): Long = {
  def f(accumulator: Long, i: Int): Long = 
    if (i == 1) accumulator
    else f(i*accumulator, i-1)
  f(1, i)
}

for(n <- 1 to 10) {
  printf("%2d: %d =? %d\n", n, factl(n), factr(n))
}
