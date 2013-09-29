import sbt._

object Dependencies {

  val resolutionRepos: Seq[MavenRepository] = Seq(
    "spray repo" at "http://repo.spray.io/",
    "spray nightlies" at "http://nightlies.spray.io"
  )

  def compile   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "compile")
  def provided  (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "provided")
  def test      (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "test")
  def runtime   (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "runtime")
  def container (deps: ModuleID*): Seq[ModuleID] = deps map (_ % "container")

  val apacheZooKeeper = "org.apache.zookeeper" % "zookeeper" % "3.4.5" exclude("javax.jms", "jms") exclude("com.sun.jdmk", "jmxtools") exclude("com.sun.jmx", "jmxri") exclude("log4j", "log4j") exclude("org.slf4j", "slf4j-log4j12") exclude("org.slf4j", "slf4j-api")
  val curatorRecipes = "org.apache.curator" % "curator-recipes" % "2.2.0-incubating"

  val googleGuice = "com.google.inject" % "guice" % "3.0"
  val googleGuiceMultibindings = "com.google.inject.extensions" % "guice-multibindings" % "3.0"
  val guava = "com.google.guava" % "guava" % "15.0"

  val akkaVersion = "2.2.1"
  val akkaActor = "com.typesafe.akka" %% "akka-actor" % akkaVersion
  val akkaSlf4j = "com.typesafe.akka" %% "akka-slf4j" % akkaVersion
  val akkaTestKit = "com.typesafe.akka" %% "akka-testkit" % akkaVersion

  val sprayVersion = "1.2-20130913"
  val sprayHttp = "io.spray" % "spray-http" % sprayVersion
  val sprayCaching = "io.spray" % "spray-caching" % sprayVersion
  val sprayCan = "io.spray" % "spray-can" % sprayVersion
  val sprayClient = "io.spray" % "spray-client" % sprayVersion
  val sprayRouting = "io.spray" % "spray-routing" % sprayVersion
  val sprayTestKit = "io.spray" % "spray-testkit" % sprayVersion

  val jacksonDatabind = "com.fasterxml.jackson.core" % "jackson-databind" % "2.2.2"
  val dnsJava = "dnsjava" % "dnsjava" % "2.1.1"

  val scalaTest = "org.scalatest" %% "scalatest" % "2.0.RC1-SNAP4"
  val mockitoAll = "org.mockito" % "mockito-all" % "1.9.5"

  val commonsDaemon = "commons-daemon" % "commons-daemon" % "1.0.15"
  val logbackClassic = "ch.qos.logback" % "logback-classic" % "1.0.13"
  val logbackGelf = "me.moocar" % "logback-gelf" % "0.10p1"
  val ravenLogback = "net.kencochrane.raven" % "raven-logback" % "4.0"

  val grizzledSlf4j = "org.clapper" %% "grizzled-slf4j" % "1.0.1"

  val typesafeConfig = "com.typesafe" % "config" % "1.0.2"

  val commonsEmail = "org.apache.commons" % "commons-email" % "1.3.1"
}