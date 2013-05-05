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
  val nexus = "https://oss.sonatype.org/"

  val main = play.Project(appName, appVersion, appDependencies).settings(
    organization := "org.zapodot",
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    publishTo <<= version { (v: String) =>
      if (v.trim.endsWith("SNAPSHOT"))
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    licenses := Seq("Apache License" -> url("http://opensource.org/licenses/Apache-2.0")),
    homepage := Some(url("https://github.com/zapodot/play-metrics")),
    pomExtra := (
      <url>https://github.com/zapodot/play-metrics</url>
        <scm>
          <url>git@github.com:zapodot/play-metrics.git</url>
          <connection>scm:git:git@github.com:zapodot/play-metrics.git</connection>
        </scm>
        <developers>
          <developer>
            <id>zapodot</id>
            <name>Sondre Eikanger Kvalø</name>
            <url>http://zapodot.github.io</url>
          </developer>
        </developers>)
  )

}
