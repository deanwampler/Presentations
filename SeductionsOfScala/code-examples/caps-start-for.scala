object CapsStartFor {
  def main(args: Array[String]) = {
    for {
      arg <- args
      if (arg(0).isUpper)
    } println("arg: " + arg)
  }
}
// $ scalac caps-start-for.scala
// $ scala -cp . CapsStartFor aB Ab AB ab
// arg: Ab
// arg: AB
