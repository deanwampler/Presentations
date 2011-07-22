// Calculate the Fibonacci numbers up to the user specified maximum.
// Then group all the numbers from 1 to the same maximum into buckets
// that are divisible by each Fibonacci number.
object GroupByFibs {

 def main(args: Array[String]) = {

	val max = args.toList match {
		case n :: tail => n.toLong
		case _ => error("Must supply the max number")
	}

	val map = groupBy(max)
	map.keySet.toList
		 .sortWith(_ < _)
		 .foreach(key => println(key + ": " + map(key)))
 }

 protected def groupBy(max: Long) = {
	 val fibs = fibsTo(max).reverse
   (1L to max).groupBy {n => fibs.find(n % _ == 0).get}
 }

 // For our purposes, we only return one 1 at the beginning.
 protected def fibsTo(n: Long): List[Long] = {
	 def fib(fib_nminus2: Long, fib_nminus1: Long): List[Long] = {
		 if (fib_nminus1 > n) {
			 Nil
		 } else {
			 val fib_n = fib_nminus2 + fib_nminus1
			 fib_n :: fib(fib_nminus1, fib_n)
		 }
	 }
	 1L :: fib(1L, 1L)
 }
}

