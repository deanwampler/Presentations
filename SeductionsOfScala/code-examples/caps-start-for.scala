object CapsStartFor {
    def main(args: Array[String]) = {
        for {
            i <- 0 to (args.length - 1)
            arg = args(i)
            if (arg(0).isUpperCase)
        }
        println("arg: "+arg)
    }
}
// $ scalac caps-start-for.scala
// $ scala -cp . CapsStartFor aB Ab AB ab
// arg: Ab
// arg: AB
