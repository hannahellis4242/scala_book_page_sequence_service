name := """book_page_sequence_service"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.12"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0" % Test
libraryDependencies += "net.debasishg" %% "redisclient" % "3.41"

//Test
libraryDependencies += "org.scalatest" %% "scalatest-funsuite" % "3.2.17" % "test"
libraryDependencies += "org.scalatest" %% "scalatest-flatspec" % "3.2.17" % "test"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
