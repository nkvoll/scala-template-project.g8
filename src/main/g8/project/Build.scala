import sbt.Keys._
import sbt._
import sbtassembly.Plugin._

object Build extends Build {
  import BuildSettings._
  import Dependencies._

  // configure prompt to show current project
  override lazy val settings = super.settings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  lazy val root = Project("root", file("."))
    //.aggregate(other, projects)
    //.settings(basicSettings: _*)
    //.settings(noPublishing: _*)
    .settings(moduleSettings: _*)
    .settings(libraryDependencies ++=
      compile(akkaActor, akkaSlf4j) ++
      compile(grizzledSlf4j) ++
      compile(jacksonDatabind) ++
      test(akkaTestKit, logbackClassic)
    )
}