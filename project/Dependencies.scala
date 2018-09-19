import sbt._

object Dependencies {
  lazy val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
  lazy val sparkCore = "org.apache.spark" %% "spark-core" % "2.3.1"
  lazy val sparkSql = "org.apache.spark" %% "spark-sql" % "2.3.1"
}
