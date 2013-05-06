import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "scala"
  val appVersion      = "1.0-SNAPSHOT"
  val metricsVersion = "3.0.0-BETA2"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "com.codahale.metrics" % "metrics-json" % metricsVersion
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    unmanagedBase <<= baseDirectory { base => base / ".." / ".." / "target" / "scala-2.10" }     
  )

}
