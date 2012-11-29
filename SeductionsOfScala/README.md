# README for the Seductions of Scala Tutorial

Dean Wampler<br/>
[dean@deanwampler.com](mailto:dean@deanwampler.com)<br/>
[@deanwampler](https://twitter.com/deanwampler)

*Copyright (c) 2010-2013, Dean Wampler. All Rights Reserved*

## Introduction

This hands-on tutorial introduces you to the Scala programming language, a modern JVM language that provides a more concise syntax than Java, addresses some of the limitations of the Java object model, and adds full support for [Functional Programming](http://www.cs.kent.ac.uk/people/staff/dat/miranda/whyfp90.pdf).

## Setup

A PDF of the slides, these instructions, and all the source code will be provided in class. You need to setup the following tools in advance.

### Scala 2.10 RC2

At the time of this writing, RC2 of version 2.10 is the latest available. Although this is not a production-ready release, the tutorial introduces you to new features in this release. We'll indicate what features are new. You'll need to install this release (or a more recent one, if available) before the tutorial.

Go to to the Scala [download page](http://www.scala-lang.org/downloads#RC) and download the 2.10 RCn installer for your platform. Download the API and samples archive, too.

| NOTE |
| :-- |
| Be sure to grab the correct version of Scala, a 2.10 "release candidate". |

Install it somewhere convenience. We'll use `$SCALA_HOME` to refer to this location. Be sure to add `$SCALA_HOME/bin` to your `PATH`.

### A Programmer's Editor or IDE

You can use any editor you want, preferably one that supports Scala syntax highlighting. Most do these days, but you may need to install a Scala plugin for your favorite editor.

If you like using Eclipse, consider installing the [Scala IDE](http://scala-ide.org/download/milestone.html). Be careful to pick to the version for Scala 2.10 and the version of Eclipse you have. IntelliJ IDEA also has good support for Scala. However, we won't need IDE features for the tutorial exercises.

### Sanity Check

Once Scala is installed, try running the REPL (real, eval, print, loop) to make sure everything is working. The commands you type outside the REPL (e.g., at the `bash` shell for Linux and Mac OS X) are shown with the `$` prompt. The `scala` prompt is `scala>`. Everything else is output.

	$ scala
	Welcome to Scala version 2.10.0-RC2 (Java HotSpot(TM) 64-Bit Server VM, Java 1.7.0_04).
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

We use *control-d* (^D) to exit. (It's an old companion to ^C that means "end of input".)

The REPL commands that begin with a colon (`:`) are used for non-code directives, like turning on the useful "power" mode, viewing help, etc.

## That's It!

You're ready to go. During the tutorial, we'll do the exercises in the `tutorial-execises` folder. The code examples that appear in the presentation are in the `code-examples` folder. The examples specific to Scala 2.10 are in the `code-examples/v2.10` folder.

I welcome feedback on the tutorial. See my contact information at the beginning of this file.


