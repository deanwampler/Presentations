// Exercise 8: Implement Your Own whileTrue Control
// I want to write:

var i = 0
whileTrue(i < 10) {
  println(i)
  i += 1
}

// Here is the declaration:
// def whileTrue(condition: => Boolean)(block: => Unit): Unit
// Note that each argument is a by-name parameter.
// Use recursion.
