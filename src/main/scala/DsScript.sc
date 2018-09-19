/*
import org.apache.spark
import org.apache.spark.sql.Dataset

type Reading = (String,String)

val o1:Dataset[Reading] = spark.read.csv("/Users/a28365/kip/kipspark/kipspark2/src/main/resources/data.csv").as[Reading]

val o2:Dataset[(String, String)] = o1.map(r => (r._1.substring(0,10), r._2))

//val o3 = o2.groupByKey( (t:(String,String)) => t._1 )

val df2 = o2.toDF("date","temperature")

df2.
  write.
  partitionBy("date").
  parquet("/Users/a28365/kip/kipspark/kipspark2/src/main/resources/df2.csv")


val ds0 = spark.read.csv("/Users/a28365/kip/kipspark/kipspark2/src/main/resources/data.csv").as[(String,String)]

val byDateDs = ds0.map(r => (r._1.substring(0,10), r._2))
val byDateDf = byDateDs.toDF("date","temperature")
def writeParquet() = byDateDf.write.partitionBy("date").parquet("/Users/a28365/kip/kipspark/kipspark2/src/main/resources/tempsByDate.parquet")

val bySecondDs = ds0.map(r => (r._1.substring(11,19), r._2))
val bySecondDf = byTimeDs.toDF("time","temperature")

//this function is taking too much time
def writeTimeParquet() = byTimeDf.write.partitionBy("time").parquet("/Users/a28365/kip/kipspark/kipspark2/src/main/resources/tempsByTime.parquet")
*/
