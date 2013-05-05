import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "metrics-java"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    unmanagedBase <<= baseDirectory { base => base / ".." / ".." / "target" / "scala-2.10" }
  )

}
