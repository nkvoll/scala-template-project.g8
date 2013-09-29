import com.typesafe.sbt.SbtScalariform
import com.typesafe.sbt.SbtScalariform.ScalariformKeys
import sbt.Keys._
import sbt._

object BuildSettings {
  val VERSION = "1.0.0-SNAPSHOT"

  lazy val basicSettings = Seq(
    version               := VERSION,
    homepage              := Some(new URL("$homepage$")),
    organization          := "$organization$",
    organizationName      := "$user_real_name$",
    organizationHomepage  := Some(new URL("$homepage$")),
    description           := "$description$",
    startYear             := Some(2013),
    scalaVersion          := "$scala_version$",
    resolvers             ++= Dependencies.resolutionRepos,
    scalacOptions         := Seq(
      "-encoding", "utf8",
      "-feature",
      "-unchecked",
      "-deprecation",
      "-target:jvm-1.7",
      "-language:_",
      "-Xlog-reflective-calls"
    )
  )

  lazy val moduleSettings =
    basicSettings ++ formatSettings ++
      net.virtualvoid.sbt.graph.Plugin.graphSettings ++
      Seq(
        // scaladoc settings
        (scalacOptions in doc) <++= (name, version).map { (n, v) => Seq("-doc-title", n, "-doc-version", v) },

        // publishing
        crossPaths := false,
        publishMavenStyle := true

        /*
        publishTo <<= version { version =>
          Some {
            "spray nexus" at {
              // public uri is repo.spray.io, we use an SSH tunnel to the nexus here
              "http://localhost:42424/content/repositories/" + {
                if (version.trim.endsWith("SNAPSHOT")) "snapshots/" else
                if (NightlyBuildSupport.isNightly) "nightlies/" else "releases/"
              }
            }
          }
        }
        */
      )

  lazy val noPublishing = Seq(
    publish := (),
    publishLocal := ()
  )

  lazy val generateVersionConf = TaskKey[Seq[File]]("generate-project-version-conf",
    "Create a reference.conf file in the managed resources folder that contains a ....version = ... setting")

  lazy val versionConfGeneration = Seq(
    (unmanagedResources in Compile) <<= (unmanagedResources in Compile).map(_.filter(_.getName != "reference.conf")),
    resourceGenerators in Compile <+= generateVersionConf,
    generateVersionConf <<= (unmanagedResourceDirectories in Compile, resourceManaged in Compile, version) map {
      (sourceDir, targetDir, version) => {
        val source = sourceDir / "reference.conf"
        val target = targetDir / "reference.conf"

        val conf = IO.read(source.get.head)

        val gitSha1 = Process("git log -1 --pretty=format:%H").lines_!.head
        val shortSha1 = gitSha1.substring(0, 5)

        IO.write(target, conf.replace("<VERSION>", version).replace("<COMMITHASH>", gitSha1).replace("<SHORTCOMMITHASH>", shortSha1))
        Seq(target)
      }
    }
  )

  lazy val docsSettings = basicSettings ++ noPublishing ++ Seq(
    unmanagedSourceDirectories in Test <<= baseDirectory { file => (file ** "code").get }
  )

  lazy val formatSettings = SbtScalariform.scalariformSettings ++ Seq(
    ScalariformKeys.preferences in Compile := formattingPreferences,
    ScalariformKeys.preferences in Test    := formattingPreferences
  )

  import scalariform.formatter.preferences._

  def formattingPreferences =
    FormattingPreferences()
      .setPreference(RewriteArrowSymbols, false)
      .setPreference(AlignParameters, true)
      .setPreference(AlignSingleLineCaseStatements, true)
      .setPreference(DoubleIndentClassDeclaration, true)

}