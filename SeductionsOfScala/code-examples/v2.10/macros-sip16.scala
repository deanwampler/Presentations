// Hygienic Macros
// New to Scala 2.10 (SIP-16)
//
// The macros feature is still evolving. Here's a good starting
// place for details on the current implementation and future plans:
// http://scalamacros.org/index.html
//
// In fact, this example, the code in macors-Print-sip16.scala, is adapted from:
//   http://scalamacros.org/documentation/gettingstarted.html

// Compile the "Print" example file first:
//   scalac macros-Print-sip16.scala
// Then start the scala REPL and load this file. Note that you'll
// need to add the current directory to the CLASSPATH to pick up the
// compiled code: 
//   scala -cp .
//
// Now, if you want to see how macros are expanded into code,
// start the REPL thusly:
//   scala -cp . -Ymacro-debug-lite
//
// Then, when the code that follows references Print.printf from
// macros-examples-sip16.scala, you'll see information about the transformation.

for {
  i <- 1 to 3
  s = s"$i is an integer"
} Print.printf("An int %d, a string \"%s\", and a literal %%.\n", i, s)
