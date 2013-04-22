import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-metrics"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,
    "com.codahale.metrics" % "metrics-json" % "3.0.0-BETA2"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here      
  )

}
