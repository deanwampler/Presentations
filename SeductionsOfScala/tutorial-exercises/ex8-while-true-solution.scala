// Exercise 8: Implement Your Own whileTrue Control
// I want to write:
// You can run this file with "scala ex8-while-true-solution.scala"

// Implementation of whileTrue:
def whileTrue(condition: => Boolean)(block: => Unit): Unit =
  if (condition) {
    block
    whileTrue(condition)(block)
  }

var i = 0
whileTrue(i < 10) {
  println(i)
  i += 1
}

// Here is a slightly different form that uses a nested method. Note that
// condition and block are still in scope inside the nested method:
def whileTrue2(condition: => Boolean)(block: => Unit): Unit = {
  def wt: Unit = if (condition) {
    block
    wt
  }
  wt
}

whileTrue2(i < 20) {
  println(i)
  i += 1
}

