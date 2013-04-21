# README for the Actors Example in "Seductions of Scala"

Dean Wampler<br/>
[dean@concurrentthought.com](mailto:dean@concurrentthought.com)<br/>
[dean@deanwampler.com](mailto:dean@deanwampler.com)<br/>
[@deanwampler](https://twitter.com/deanwampler)

*Copyright (c) 2009-2013, Dean Wampler. All Rights Reserved*

The Actors example for the [Seductions of Scala](https://github.com/deanwampler/Presentations/tree/master/SeductionsOfScala) presentation and tutorial. This code can be found in the [code-examples/actors](https://github.com/deanwampler/Presentations/tree/master/SeductionsOfScala/code-examples/actors) subdirectory.

This is a simple example using the [Akka Framework](http://akka.io) v2.1.X for Scala and Java, with Scala v2.10.X.

The example is built and run using the [sbt](http://www.scala-sbt.org/), the de facto standard build tool for Scala projects. You'll need to install `sbt` to build this project. 

## Instructions

Change to the root directory for the actors example `SeductionsOfScala/code-examples/actors`. 

Using `bash` or another command shell, run `sbt`, as instructed by the `sbt` installation process.

At the `sbt` prompt (`>`), run the following commands. (The #... are comments and shouldn't be typed).

    compile  # Compile the code. (Expect a "success" message when done).
    run      # Run the actor example.
    quit     # Exit sbt.

The first time start `sbt` and compile the code, it may take a long time to download the required dependencies. 

The fairly verbose output of the commands should end with the following for a success run:

    ...
    [info] Running shapes.ShapesDrawingDriver
    -> drawing: Circle(Point(0.0,0.0),1.0)
    -> drawing: Rectangle(Point(0.0,0.0),2.0,5.0)
    <- Shape drawn.
    -> Error: 3.14159
    <- Shape drawn.
    -> exiting...
    <- Unknown message: 3.14159
    <- cleaning up...
    [success] Total time: 6 s, completed Apr 21, 2013 5:35:23 PM

The messages starting with `->` are written by the **drawing** actor that receives drawing commands from the **driver** actor. The messages starting with `<-` are written by the driver actor. They are the replies received from the drawing actor.

Note that the interleaving of `<-` and `->` messages will be *arbitrary*, **but**, the `->` messages will *always* be in the same order and the `<-` messages will *always* be in the same order, because the drawing actor processes the messages it receives in order, one at a time.

The drawing actor is in `src/main/scala/ShapesDrawingActor.scala`, while the driver actor is in `src/main/scala/ShapesDrawingDriver.scala`.


