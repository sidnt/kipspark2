package kipspark2

import Helpers._
import org.apache.spark.sql.{Dataset, SparkSession}

import scala.io.StdIn
import scala.util.{Failure, Success, Try}

object UserApp extends App {

  lazy val spark: SparkSession =
    SparkSession
      .builder()
      .appName("UserFacingApp")
      .config("spark.master", "local[*]")
      .getOrCreate()

  def interact():Unit = {

    val choice:Try[Int] =
      Try(

          StdIn.readLine(
          "Enter Choice\n" +
            "0 - Exit\n" +
            "1 - Count all temperatures on a specific date\n" +
            "2 - Count all temperatures within a time range\n" +
            "3 - Print all temperatures on a specific date\n" +
            "4 - Print all temperatures within a time range\n"
          ).toInt

      )

    choice match {
      case Success(ch) => ch match {
        case 0 => ()

        case 1 => {

          val dateString:Try[String] = Try(StdIn.readLine("Enter Date in yyyy-mm-dd format\n") match { case Date(s) => s } )
          lazy val rawDfByDate = spark.read.parquet(FilePaths.byDateParquet)
          def collectTempsOnDate(d: String) = {
            val answer = rawDfByDate.select("temperature").where(s"date == '$d'")
            answer.collect().map(_.toString).mkString(", ")
          }
          dateString match {
            case Success(d) => { println(collectTempsOnDate(d)); interact() }
            case Failure(ex) => { println("Wrong input: " + ex.toString); interact() }
          }
        }

        case 2 => {
          val timeRange:Try[(String,String)] = Try(StdIn.readLine("Enter time range in hh:mm-hh:mm format\n") match { case TimeRange(s) => s } )
          lazy val rawDfByMinute = spark.read.parquet(FilePaths.byMinuteParquet)
          def collectTempsInRange(tr:(String,String)) = {
            rawDfByMinute.createOrReplaceTempView("tempsByMinute")
            //val answer = rawDfByMinute.select("temperature").where
            val answer = spark.sql(s"SELECT temperature from tempsByMinute WHERE minute BETWEEN '${tr._1}' and '${tr._2}'")
            //answer.count//collect().map(_.toString).mkString(", ")
            answer.collect().map(_.toString).mkString(", ")
          }

          timeRange match {
            case Success(tr) => { println(collectTempsInRange(tr)); interact() }
            case Failure(ex) => { println("Wrong input: " + ex.toString); interact() }
          }
        }

        case x => { println("not an option " + x ) ; interact() }
      }
      case Failure(ex) => { println("invalid input: " + ex.toString) ; interact() }
    }
  }

  interact()

  /*






  def printTempsOnDate(d: String) = {
    rawDfByDate.select("temperature").where(s"date == '$d'").show(2000)//collect.map(_.toString.substring(1,5)) foreach
  }*/


  //spark.sql(s"SELECT * from tempsByMinute WHERE minute BETWEEN '03:33' and '03:33'").show(100)





/*

  def printTempsInRange(tr: (String, String)):Unit = {
//    val (f,t):(String,String) = tr
//    println(s"SELECT * from tempsByMinute WHERE minute BETWEEN '$f' and '$t'")


    println(selected.count())
  }*/



  //val inputString = Try(StdIn.readLine("Enter Date in yyyy-mm-dd format\nor time range in hh:mm-hh:mm format\n") match { case Date(s) => s } )
}
/*


import scala.util.{Try, Success, Failure}

def divide: Try[Int] = {
  val dividend = Try(StdIn.readLine("Enter an Int that you'd like to divide:\n").toInt)
  val divisor = Try(StdIn.readLine("Enter an Int that you'd like to divide by:\n").toInt)
  val problem = dividend.flatMap(x => divisor.map(y => x/y))
  problem match {
  case Success(v) =>
  println("Result of " + dividend.get + "/"+ divisor.get +" is: " + v)
  Success(v)
  case Failure(e) =>
  println("You must've divided by zero or entered something that's not an Int. Try again!")
  println("Info from the exception: " + e.getMessage)
  divide
}
}*/
