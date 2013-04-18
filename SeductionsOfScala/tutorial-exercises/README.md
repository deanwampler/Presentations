# README for the Tutorial Exercises

Dean Wampler<br/>
[dean@polyglotprogramming.com](mailto:dean@polyglotprogramming.com)<br/>
[@deanwampler](https://twitter.com/deanwampler)<br/>
April 26, 2013

Here are the exercises for the tutorial. We may not have time for all of them. The file names follow this convention:

* **exN-name.scala:** The file defining exercise number **N**, with name **name**.
* **solutions/exN-name.scala:** My solution to the exercise, with separate solutions for Scala v2.9 and v2.10 in some cases.

Of course, other solutions are often possible.

To run an exercise, e.g., the first one,

    scala -cp . ex1-classes.scala

If you get an error that `CheapTests` can't be found, then compile the "cheap tests" library:

    scalac -cp . cheap-tests.scala

I welcome feedback on the tutorial and on these exercises!