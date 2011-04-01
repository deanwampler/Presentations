object CapsStartList {
    def main(args: Array[String]) = {
        val capsList = for {
            i <- 0 to (args.length - 1)
            arg = args(i)
            if (arg(0).isUpperCase)
        }
        yield arg
        println("list: "+capsList)
    }
}
// $ scalac caps-start-for.scala
// $ scala -cp . CapsStartFor aB Ab AB ab
// list: RangeMFM(Ab, AB)