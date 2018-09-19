import Dependencies._

lazy val kipspark2 = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.knoldus",
      scalaVersion := "2.11.12",
      version      := "0.0.1"
    )),
    name := "kipspark2",
    libraryDependencies ++= Seq(
      scalaTest % Test,
      sparkCore,
      sparkSql
    )
  )
