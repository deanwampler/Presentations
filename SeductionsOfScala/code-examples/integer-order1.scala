
// What does this do??
object Main1 {

 def main(args: Array[String]) = {

  args.map(s => s.toInt)
      .toSet
      .toList
      .sortWith((x,y) => x < y)
      .foreach(x => println(x))
 }
}
