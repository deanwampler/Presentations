import sbt._
class Plugins(info: ProjectInfo) extends PluginDefinition(info) {
  val EmbeddedRepo   = MavenRepository("Embedded Repo", (info.projectPath / "embedded-repo").asURL.toString)
  val LocalMavenRepo = MavenRepository("Local Maven Repo", (Path.userHome / ".m2" / "repository").asURL.toString)

//  val scctRepo     = "scct-repo" at "http://mtkopone.github.com/scct/maven-repo/"
  val akkaRepo     = "akka-repo" at "http://akka.io/repository"
  val sbtIdeaRepo  = "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"
  val sbtNexusRepo = "sonatype-nexus-repo" at "http://nexus.scala-tools.org/content/repositories/releases/"

//  val scctPlugin = "reaktor" % "sbt-scct-for-2.8" % "0.1-SNAPSHOT"
  val akkaPlugin = "se.scalablesolutions.akka" % "akka-sbt-plugin" % "1.0"
  val sbtIdea    = "com.github.mpeltonen" % "sbt-idea-plugin" % "0.4.0"
  val sbtEclipse = "de.element34" % "sbt-eclipsify" % "0.7.0"
}
