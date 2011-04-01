object Fib {
  def apply(n: Int): Int = n match {
    case 0 => 0
    case 1 => 1
    case _ => Fib(n-1) + Fib(n-2)
  }
}

for(n <- 0 to 10) {
  format("%2d: %d\n", n, Fib(n))
}
