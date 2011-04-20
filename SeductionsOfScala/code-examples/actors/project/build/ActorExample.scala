import sbt._
import sbt.CompileOrder._
import de.element34.sbteclipsify._

class ActorExample(info: ProjectInfo) extends DefaultProject(info)
		with AkkaProject with IdeaProject with Eclipsify with Exec {

  lazy val EmbeddedRepo   = MavenRepository("Embedded Repo", (info.projectPath / "embedded-repo").asURL.toString)
  lazy val LocalMavenRepo = MavenRepository("Local Maven Repo", (Path.userHome / ".m2" / "repository").asURL.toString)
  lazy val AkkaRepo       = MavenRepository("Akka Repository", "http://akka.io/repository")

  val akkaTypedActor = akkaModule("typed-actor")
  val akkaKernel     = akkaModule("kernel")

  lazy val embeddedRepo   = EmbeddedRepo
  lazy val localMavenRepo = LocalMavenRepo

  override def repositories = Set(AkkaRepo, EmbeddedRepo)

  override def libraryDependencies = Set(akkaTypedActor, akkaKernel) map (_ % "compile")

  override def compileOptions = super.compileOptions ++
    Seq("-deprecation",
        "-unchecked",
        "-Xmigration",
        "-Xcheckinit",
        "-Xwarninit",
        "-encoding", "utf8")
        .map(x => CompileOption(x))

//  override def mainClass = Some("shapes.ShapesDrawingDriver")
  override def mainClass = Some("shapes.SyncShapesDrawingDriver")
}
