// Exercise 9: Experiment with Collection Operations.
// Starting with the "CapsStart" application, experiment with passing
// different functions to filter and foreach.
// Try different combinations of other collections functions, like map, 
// foldLeft/foldRight, reduceLeft/reduceRight, etc.
// You can run this file with "scala ex9-collection-operations-solution.scala"

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

// Look at the Scaladocs for reduceLeft and try to figure out how it works, based
// on this use of it:
def arrayToNiceString(ary: Array[_]) = "[" + (ary reduceLeft(_ + ", " + _)) + "]"

val list = Array("aB", "Ab", "AB", "ab")
println("Running \"CapStart.main\" on the list in the slides: " + arrayToNiceString(list))
m(list)

// Other options. Note that we use implicit arguments where we can and pass
// println as a function...

val list2 = list ++ Array("abc", "AbC", "aBc", "abcde", "AbCdEfG")
println("\nOther things we might do, using this list: " + arrayToNiceString(list2))
println("Filter for items that contain \"b\":")
list2 filter (_.contains("b")) foreach println
println("\nConvert all items to upper case:")
list2 map (_.toUpperCase) foreach println

println("\nCollect (fold) all the strings into one string:")
def collect(args: Array[String]) = 
  args.foldLeft("")((result, s) => result + s)
  
println("collect: " + collect(list2))

import scala.collection.immutable.TreeSet

println("\nExtract all the unique characters (respecting case) into a list:")
def uniq(args: Array[String]) = 
  args.toList  // convert to list
    .flatMap(_.toList)  // flatten the list of strings to single list of characters
    .sort(_ < _)        // sort the list alphanumerically
    .foldLeft(new TreeSet[Char])((set,s) => set + s)  // "fold" the list elements into a set
    .toList             // convert to the final list

println("uniq: " + uniq(list2))

