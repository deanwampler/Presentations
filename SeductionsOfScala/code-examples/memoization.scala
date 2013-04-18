// Use memoization with factl from factorial.scala

// This is a left-recursion implementation.
def factl(i: Int): Long = {
  def f(accumulator: Long, i: Int): Long = 
    if (i == 1) accumulator
    else f(i*accumulator, i-1)
  f(1, i)
}

case class memo(f: Int => Long) {
  // We aren't going to synchronize cache access, because
  // it's harmless if two threads right the same key-value
  // to the cache simultaneously!
  val cache = scala.collection.mutable.Map.empty[Int,Long]
  def apply(i: Int): Long = cache.get(i) match {
    case Some(l) => l
    case None => 
      val l = f(i)
      cache.put(i,l)
      l
  }
}

// Implementation without using pattern matching.
case class memo2(f: Int => Long) {
  val cache = scala.collection.mutable.Map.empty[Int,Long]
  def apply(i: Int): Long = {
    val optionl = cache.get(i)
    if (optionl == None) {
      val l = f(i)
      cache.put(i,l)
      l      
    } else {
      optionl.get
    }
  }
}

val factl2 = memo2(factl)

for(n <- 1 to 10) {
  printf("%2d: %d\n", n, factl2(n))
}
