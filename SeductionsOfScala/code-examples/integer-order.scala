
object Main {

 def main(args: Array[String]) = {

  args.map(_.toInt)
      .toSet
      .toList
      .sortWith(_ < _)
      .foreach(println(_))
 }
}

object MainExpanded {

 def main(args: Array[String]) = {

  args.map(s => s.toInt)
      .toSet
      .toList
      .sortWith((x,y) => x < y)
      .foreach(x => println(x))
 }
}
