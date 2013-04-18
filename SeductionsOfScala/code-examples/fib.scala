// Note that we aren't checking for negative arguments!

// Here is a pattern-matching implementation, the simplest form.
def fib(n: Int): Int = n match {
  case 0 => 0
  case 1 => 1
  case _ => fib(n-1) + fib(n-2)
}

for(n <- 0 to 10) {
  printf("%2d: %d\n", n, fib(n))
}
