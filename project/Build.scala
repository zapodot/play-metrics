import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "play-metrics"
  val appVersion      = "1.0-SNAPSHOT"

  val metricsVersion = "3.0.0-BETA2"

  val appDependencies = Seq(
    javaCore,
    "com.codahale.metrics" % "metrics-json" % metricsVersion,
    "com.codahale.metrics" % "metrics-annotation" % metricsVersion,
    "junit" % "junit" % "4.11" % "test"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    organization := "org.zapodot",
    publishTo := Some(Resolver.file("file",  new File( "../zapodot.github.io/repo/snapshots" )) )
  )

}
