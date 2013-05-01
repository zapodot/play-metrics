import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "metrics-java"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    "play-metrics" %% "play-metrics" % "1.0-SNAPSHOT" changing()
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += "Zapodot snapshots" at "http://zapodot.github.io/repo/snapshots/"  
)

}
