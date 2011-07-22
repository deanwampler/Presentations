// Group a list of integers by their greatest divisor that is
// is a Fibonacci number.

// Note: we don't need two 1s for this...
val inverseFibs = (1 :: 2 :: 3 :: 5 :: 8 :: 13 :: 21 :: 34 :: 55 :: Nil).reverse

// Group by greatest divisor that's a Fibonacci number.
val map = (1 to 100) groupBy (n => inverseFibs.find(n % _ == 0).get)

// Present in sorted order.
map.keySet.toList
	 .sortWith(_ < _)
	 .foreach(key => println(key + ": " + map(key)))

