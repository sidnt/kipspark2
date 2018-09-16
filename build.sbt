import Dependencies._

lazy val kipspark2 = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.knoldus",
      scalaVersion := "2.12.6",
      version      := "0.0.1"
    )),
    name := "kipspark2",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      spark
    )
  )
