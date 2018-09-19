/*
import org.apache.spark.rdd.RDD

type Reading = (String,String,String)

def transformRow(r: String):Reading = (r.substring(0,10),r.substring(11,19),r.substring(25))

val r1:RDD[String] = sc.textFile("/Users/a28365/kip/kipspark/kipspark2/src/main/resources/data.csv")

val r2:RDD[Reading] = r1.map(transformRow(_))

def gFx(r:Reading):String = r._1

val r3:RDD[(String, Iterable[String])] = r2.groupBy(gFx(_)).mapValues(ir => ir.map(r => r._3)) 
*/
