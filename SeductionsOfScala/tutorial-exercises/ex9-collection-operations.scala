// Exercise 9: Experiment with Collection Operations.
// Starting with the "CapsStart" application, experiment with passing
// different functions to filter and foreach.
// Try different combinations of other collections functions, like map, 
// foldLeft/foldRight, reduceLeft/reduceRight, etc.
// You can run this file with "scala ex9-collection-operations.scala"

object CapsStart { 
  def main(args: Array[String]) = {
    args.filter((arg) => arg(0).isUpperCase) 
        .foreach((arg) => println(arg))
  }
}

// To make it easier to work with the example, I'm going to invoke "main" directly,
// so I don't have to do separate compile/run cycles. For fun, let's play with
// the import statement:

import CapsStart.{main => m}

val list = Array("aB", "Ab", "AB", "ab")
m(list)

