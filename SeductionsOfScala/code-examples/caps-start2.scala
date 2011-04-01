object CapsStart {
  def main(args: Array[String]) = {
    args filter (_(0).isUpperCase) foreach ((arg) => println("arg: "+arg))
//    args filter (_(0).isUpperCase) foreach (println("arg: "+_))
    args filter (_(0).isUpperCase) map ("arg: "+_) foreach println
  }
}
// $ scalac caps-start.scala
// $ scala -cp . CapsStart aB Ab AB ab
// arg: Ab
// arg: AB
