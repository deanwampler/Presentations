# README for the Seductions of Scala Tutorial

Dean Wampler<br/>
[dean@deanwampler.com](mailto:dean@deanwampler.com)<br/>
[@deanwampler](https://twitter.com/deanwampler)

*Copyright (c) 2009-2013, Dean Wampler. All Rights Reserved*

## Introduction

This hands-on tutorial introduces you to the Scala programming language, a modern JVM language that provides a more concise syntax than Java, addresses some of the limitations of the Java object model, and adds full support for [Functional Programming](http://www.cs.kent.ac.uk/people/staff/dat/miranda/whyfp90.pdf).

## Setup

A PDF of the tutorial slides and all the exercise source code will be provided in class. You need to setup the following tools in advance, if you can.

### Scala 2.10

At the time of this writing, version 2.10 is the latest available version of Scala. Even if you have 2.9.X, we'll discuss some features new to 2.10, so I recommend installing it. I'll indicate what features are new. However, most of the material works for 2.9.X.

Go to to the Scala [download page](http://www.scala-lang.org/downloads) and download the 2.10 installer for your platform. Also, download the API archive that appears in the same list of download links.

| NOTE |
| :-- |
| Be sure to grab the installer for version 2.10 of Scala. However, most of the tutorial will apply to the current production release, 2.9.X. |

Install Scala somewhere convenient. We'll use `$SCALA_HOME` to refer to this location. Be sure to add `$SCALA_HOME/bin` to your `PATH`.

### A Programmer's Editor or IDE

You can use any editor you want, preferably one that supports Scala syntax highlighting. Most do these days, but you may need to install a Scala plugin for your favorite editor.

If you like using Eclipse, consider installing the [Scala IDE](http://scala-ide.org/download/milestone.html). Be careful to pick to the version of the IDE for your version of Scala (e.g., 2.10) and the version of Eclipse you have (e.g., Indigo). IntelliJ IDEA also has good support for Scala. However, we won't need IDE features for the tutorial exercises.

### Sanity Check

Once Scala is installed, try running the REPL (Read, Eval, Print, Loop) to make sure everything is working. The commands you type outside the REPL (e.g., at the `bash` shell for Linux and Mac OS X) are shown with the `$` prompt. The `scala` prompt is `scala>`. Everything else is output. Your version details my differ slightly.

	$ scala
	Welcome to Scala version 2.10.0 (Java HotSpot(TM) 64-Bit Server VM, Java 1.7.0).
	Type in expressions to have them evaluated.
	Type :help for more information.

	scala> :power
	** Power User mode enabled - BEEP WHIR GYVE **
	** :phase has been set to 'typer'.          **
	** scala.tools.nsc._ has been imported      **
	** global._, definitions._ also imported    **
	** Try  :help, :vals, power.<tab>           **

	scala> :help
	All commands can be abbreviated, e.g. :he instead of :help.
	Those marked with a * have more detailed help, e.g. :help imports.

	:cp <path>                 add a jar or directory to the classpath
	:help [command]            print this summary or command-specific help
	:history [num]             show the history (optional num is commands to show)
	:h? <string>               search the history
	:imports [name name ...]   show import history, identifying sources of names
	:implicits [-v]            show the implicits in scope
	:javap <path|class>        disassemble a file or class name
	:load <path>               load and interpret a Scala file
	:paste                     enter paste mode: all input up to ctrl-D compiled together
	:power                     enable power user mode
	:quit                      exit the interpreter
	:replay                    reset execution and replay all previous commands
	:reset                     reset the repl to its initial state, forgetting all session entries
	:sh <command line>         run a shell command (result is implicitly => List[String])
	:silent                    disable/enable automatic printing of results
	:type [-v] <expr>          display the type of an expression without evaluating it
	:warnings                  show the suppressed warnings from the most recent line which had any
	:phase <phase>             set the implicit phase for power commands

	scala> ^D

I used *control-d* (^D) to exit. It's an old terminal control, similar to ^C, that means "end of input". It's more "polite" than ^C...

The REPL commands that begin with a colon (`:`) are used for non-code directives, like turning on the useful "power" mode, viewing help, loading source files, etc.

### Open the API in your browser.

It's convenient to open the API documentation you downloaded in a browser. The *Scaladocs* are analogous to *Javadocs*. See also the [References][References] below.

## That's It!

You're ready to go. 

I welcome feedback on the tutorial. See my contact information at the beginning of this file.

## References [References]

### General Scala Resources

[scala-lang.org](https://scala-lang.org)
: The main website for Scala.

[Typesafe](https://typesafe.com)
: The official developer of Scala and other components that are part of the [Typesafe stack](https://typesafe.com/stack).

[docs.scala-lang.org](http://docs.scala-lang.org)
: Lots of documentation for Scala, including the [Scaladocs](http://scala-lang.org/archives/downloads/distrib/files/nightly/docs/library/index.html) tutorials, the *Scala Improvement Process* (SIP) proposals (for new features), etc.

[akka.io](http://akka.io)
: The website for the Akka distributed computing library that's part of the [Typesafe stack](https://typesafe.com/stack).

[Play Framework](http://www.playframework.org/)
: One of many web frameworks for Scala, this one is an official part of the [Typesafe stack](https://typesafe.com/stack).

[Programming Scala](http://ofps.oreilly.com/titles/9780596155957/)
: The book I co-wrote with Alex Payne. It's a bit dated, to be honest, but we have plans to update it soon. Links for other books can be found at [scala-lang.org](https://scala-lang.org).

### What's New in Scala Version 2.10?

Here are two nice lists of the new features with links to more details:

* [http://ochsenreither.posterous.com/whats-new-in-210](http://ochsenreither.posterous.com/whats-new-in-210)
* [http://scala-programming-language.1934581.n4.nabble.com/Scala-2-10-0-RC2-td4634084.html](http://scala-programming-language.1934581.n4.nabble.com/Scala-2-10-0-RC2-td4634084.html).

### Specifically on *Macros*

*Macros* provide a *hygienic macro* facility, somewhat like Lisp and not like C or C++. It's one of the most significant new features that promises to revolutionalize API and DSL development. It's an evolving feature. The principle documentation is available here:

* [http://scalamacros.org](http://scalamacros.org)

The following projects are using Macros. The first three are referenced on [this scalamacros.org "getting started" page](http://scalamacros.org/documentation/gettingstarted.html):

* [https://github.com/retronym/macrocosm](https://github.com/retronym/macrocosm)
* [https://github.com/pniederw/expecty](https://github.com/pniederw/expecty)
* [http://slick.typesafe.com/](http://slick.typesafe.com/)
* Play 2.1 JSON API: [http://mandubian.com/2012/11/11/JSON-inception/](http://mandubian.com/2012/11/11/JSON-inception/)

### Slick

Slick is a type-safe, data-access API inspired by .NET's [LINQ](http://msdn.microsoft.com/en-us/library/bb397926.aspx).

* [http://slick.typesafe.com/](http://slick.typesafe.com/).


