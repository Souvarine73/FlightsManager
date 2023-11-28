import sbt.Keys.libraryDependencies
import sbtassembly.AssemblyPlugin.defaultShellScript

ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / scalaVersion := "2.13.12"
ThisBuild / assemblyPrependShellScript := Some(defaultShellScript)


val mainClassName = "org.ntic.entregable.FlightsLoader"


lazy val root = (project in file("."))
  .settings(
    name := "flightsmanager",
    Compile / run / mainClass := Some(mainClassName),
    Compile / packageBin / mainClass := Some(mainClassName),
    assembly / mainClass := Some(mainClassName),
    assembly / assemblyJarName := "flightsmanager.jar",

    libraryDependencies ++= Seq(
      "com.typesafe" % "config" % "1.4.2",
      "org.scalatest" %% "scalatest" % "3.2.17" % Test,
      "org.scala-lang" %% "toolkit-test" % "0.1.7" % Test
    )
  )
