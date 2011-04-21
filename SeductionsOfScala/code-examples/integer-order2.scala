// What does this do??
object Main2 {

 def main(args: Array[String]) = {

  args.map(_.toInt)
      .toSet
      .toList
      .sortWith(_ < _)
      .foreach(println(_))
 }
}
