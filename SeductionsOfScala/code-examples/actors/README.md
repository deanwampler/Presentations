# README for the Actors Example in "Seductions of Scala"

Dean Wampler<br/>
[dean@deanwampler.com](mailto:dean@deanwampler.com)<br/>
[@deanwampler](https://twitter.com/deanwampler)

*Copyright (c) 2009-2013, Dean Wampler. All Rights Reserved*

The Actors example for the [Seductions of Scala](https://github.com/deanwampler/Presentations/tree/master/SeductionsOfScala) presentation and tutorial. This code can be found in the [code-examples/actors](https://github.com/deanwampler/Presentations/tree/master/SeductionsOfScala/code-examples/actors) subdirectory.

This is a simple example using the [Akka Framework](http://akka.io) for Scala and Java. The Scala library had its own Actor library, but it is now deprecated in favor of Akka's.

The example is built and run using the ](http://code.google.com/p/simple-build-tool/), a.k.a `sbt`, which is included, for convenience.

## Instructions

Change to the root directory for the actors example `SeductionsOfScala/code-examples/actors`. 

Using `bash` or a similar shell, run `./sbt`.

At the `sbt` prompt (">"), run the following commands. (The #... are comments).

    update   # Download dependencies, such as the Akka libraries.
    compile  # Compile the code. (Expect a "success" message when done).
    run      # Run the actor example.
    quit     # Exit sbt.

The `update` command will take a long time the first time you run it. 

The verbose output of the run command will look something like this:

    [info] == run ==
    [info] Running shapes.Driver 
    20:19:38.528 [run-main] INFO  akka.config.Config$ - Config [akka.conf] loaded from the application classpath.
    20:19:38.807 [run-main] DEBUG a.d.Dispatchers$globalExecutorBasedEventDrivenDispatcher$ - Starting up Dispatchers$globalExecutorBasedEventDrivenDispatcher$[akka:event-driven:dispatcher:global]
            with throughput [5]
    20:19:38.809 [run-main] INFO  a.d.LazyExecutorServiceWrapper - Lazily initializing ExecutorService for 
    20:19:38.812 [akka:event-driven:dispatcher:global-1] DEBUG akka.dispatch.MonitorableThread - Created thread akka:event-driven:dispatcher:global-1
    20:19:38.871 [akka:event-driven:dispatcher:global-2] DEBUG akka.dispatch.MonitorableThread - Created thread akka:event-driven:dispatcher:global-2
    -> drawing: Circle(Point(0.0,0.0),1.0)
    -> drawing: Rectangle(Point(0.0,0.0),2.0,5.0)
    20:19:38.891 [akka:event-driven:dispatcher:global-3] DEBUG akka.dispatch.MonitorableThread - Created thread akka:event-driven:dispatcher:global-3
    <- Shape drawn.
    <- Shape drawn.
    -> Error: 3.14159
    -> exiting...
    20:19:38.893 [akka:event-driven:dispatcher:global-4] DEBUG akka.dispatch.MonitorableThread - Created thread akka:event-driven:dispatcher:global-4
    <- Unknown message: 3.14159
    <- cleaning up...
    20:19:38.925 [akka:event-driven:dispatcher:global-4] INFO  akka.actor.Scheduler$ - Starting up Scheduler
    20:19:39.928 [akka:scheduler-0] DEBUG a.d.Dispatchers$globalExecutorBasedEventDrivenDispatcher$ - Shutting down Dispatchers$globalExecutorBasedEventDrivenDispatcher$[akka:event-driven:dispatcher:global]
    20:19:39.928 [akka:event-driven:dispatcher:global-3] DEBUG akka.dispatch.MonitorableThread - Exiting thread akka:event-driven:dispatcher:global-3
    20:19:39.930 [akka:event-driven:dispatcher:global-4] DEBUG akka.dispatch.MonitorableThread - Exiting thread akka:event-driven:dispatcher:global-4
    20:19:39.930 [akka:event-driven:dispatcher:global-2] DEBUG akka.dispatch.MonitorableThread - Exiting thread akka:event-driven:dispatcher:global-2
    20:19:39.930 [akka:event-driven:dispatcher:global-1] DEBUG akka.dispatch.MonitorableThread - Exiting thread akka:event-driven:dispatcher:global-1
    [info] == run ==
    [success] Successful.
    [info] 
    [info] Total time: 2 s, completed Apr 1, 2011 8:19:39 PM

All the messages of the form `20:19:38.809 [...] ...` are Akka log messages. The actual output of the actor example is the following:

    -> drawing: Circle(Point(0.0,0.0),1.0)
    -> drawing: Rectangle(Point(0.0,0.0),2.0,5.0)
    <- Shape drawn.
    <- Shape drawn.
    -> Error: 3.14159
    -> exiting...
    <- Unknown message: 3.14159
    <- cleaning up...

The messages starting with "->" are written by the **drawing** actor that receives drawing commands from the **driver** actor. The drawing actor is in `src/main/scala/shapes-actor.scala`, while the driver actor is in `src/main/scala/shapes-actor-driver.scala`.

The messages starting with "<-" are written by the driver actor. They are the replies received from the drawing actor.

Note that the interleaving of "<-" and "->" messages will be arbitrary, **but**, the "->" messages will always be in the same order and the "<-" messages will *always* be in the same order! 

