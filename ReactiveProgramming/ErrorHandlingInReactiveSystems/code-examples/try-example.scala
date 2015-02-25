// Example using Try.
// First, compile Order.scala in this directory:
//   scalac Order.scala
// Then run this script:
//   scala try-example.scala

val good = Seq(
  "100\t23.5\t1\t1\t3\t2\t5\t3",
  "100\t23.5")
val bad = Seq(
  "100\t23.5\t1\t1\t3\t2\t5\t",
  "100\t23.5\t1\t1\t3\t2\t5",
  "\t23.5\t1\t1\t3\t2\t5\t3",
  "23.5\t1\t1\t3\t2\t5\t3",
  "100\t\t1\t1\t3\t2\t5\t3",
  "foo\t23.5\t1\t1\t3\t2\t5\t3",
  "100\tfoo\t1\t1\t3\t2\t5\t3")

val goods = good map Order.parse
val bads  = bad  map Order.parse

println("Results parsing 'good' strings:")
goods foreach println

println("\nResults parsing 'bad' strings:")
bads foreach println
