// Generic SBT build file for an Akka project
import sbt._
import sbt.Keys._
import com.typesafe.startscript.StartScriptPlugin
import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys

object AkkaActorExampleBuild extends Build {
  val Organization = "ConcurrentThought"
  val Version      = "2.0"
  val ScalaVersion = "2.10.1"

  lazy val example = Project(
    id = "example",
    base = file("."),
    settings = defaultSettings ++ 
      StartScriptPlugin.startScriptForClassesSettings ++
      Seq(libraryDependencies ++= Dependencies.akkademo)
  )

  lazy val buildSettings = Seq(
    organization := Organization,
    version      := Version,
    scalaVersion := ScalaVersion
  )

  lazy val defaultSettings = Defaults.defaultSettings ++ formatSettings ++ buildSettings ++ Seq(
    // compile options
    scalacOptions ++= Seq("-encoding", "UTF-8", "-optimise", "-deprecation", "-unchecked"),
    javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation"),

    // disable parallel tests
    parallelExecution in Test := false
  )

  // Code formatting with Scalariform:
  lazy val formatSettings = SbtScalariform.scalariformSettings ++ Seq(
    ScalariformKeys.preferences in Compile := formattingPreferences,
    ScalariformKeys.preferences in Test    := formattingPreferences
  )

  def formattingPreferences = {
    import scalariform.formatter.preferences._
    FormattingPreferences()
      .setPreference(RewriteArrowSymbols, true)
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
  }
}

object Dependencies {
  import Dependency._
  val akkademo = Seq(akkaActor, akkaRemote, scalaTest, jUnit)
}

object Dependency {
  object Version {
    val Akka      = "2.1.2"
    val Scalatest = "1.9.1"
    val JUnit     = "4.10"
  }

  // ---- Application dependencies ----

  val akkaActor   = "com.typesafe.akka"   %% "akka-actor"              % Version.Akka
  val akkaRemote  = "com.typesafe.akka"   %% "akka-remote"             % Version.Akka

  // ---- Test dependencies ----

  val scalaTest   = "org.scalatest"       %% "scalatest"               % Version.Scalatest  % "test"
  val jUnit       = "junit"               % "junit"                    % Version.JUnit      % "test"
}
