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

# Actors and Scalding Exercises

The actor exercises are in the `tutorial-exercises/drawing-actors` directory. See the README there.

The Scalding exercises mentioned in the slides are actually taken from my [Scalding-Workshop](https://github.com/deanwampler/scalding-workshop). Note that the [GitHub version of Scalding](https://github.com/twitter/scalding) builds with Scala 2.9.X, while this tutorial uses 2.10.X. Simply clone that repo, edit `target/Build.scala` and change the `scalaVersion` value to "2.10.1", then run these `sbt` commands:

    sbt
    > update
    > test
    > assembly

Finally, **replace** the `lib/scalding-assembly-0.7.3.jar` in the `scalding-workshop` with the newly-built `scalding-core/target/scala-2.10/scalding-core-assembly-0.9.0-....jar` file. Now you can use Scala 2.10 with the `scalding-workshop` scripts. I'll update that project soon with these changes, so check the README there first...

