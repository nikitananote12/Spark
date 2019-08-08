import AssemblyPlugin._
import sbt.Resolver // put this at the top of the file,leave the next line blank

name := "DataFrame"

version := "0.1"

scalaVersion := "2.12.8"
// https://mvnrepository.com/artifact/org.apache.spark/spark-core
libraryDependencies += "org.apache.spark" %% "spark-core" % "2.4.3"
// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.4.3"

name := "DataFrame"
resolvers += Resolver.url("bintray-sbt-plugins", url("http://dl.bintray.com/sbt/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += Resolver.bintrayIvyRepo("com.eed3si9n", "sbt-plugins")

lazy val commonSettings = Seq(
  organization := "com.bridgelabz",
  version := "0.1.0-SNAPSHOT"
)

// set the main class for packaging the main jar
mainClass in (Compile, packageBin) := Some("com.dataFrame")

// set the main class for the main 'sbt run' task
mainClass in (Compile, run) := Some("com.dataFrame")


resolvers in Global ++= Seq(
  "Sbt plugins" at "https://dl.bintray.com/sbt/sbt-plugin-releases",
  "Maven Central Server" at "http://repo1.maven.org/maven2",
  "TypeSafe Repository Releases" at "http://repo.typesafe.com/typesafe/releases/",
  "TypeSafe Repository Snapshots" at "http://repo.typesafe.com/typesafe/snapshots/"
)

lazy val root = (project in file(".")).
  settings(
    name := "DataFrame",
    version := "0.1",
    scalaVersion := "2.12.8",
    Compile / mainClass := Some("com.dataFrame")
  )
  .settings(commonSettings: _*)
  .enablePlugins(AssemblyPlugin)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs@_*) => MergeStrategy.discard
  case x => MergeStrategy.first
}