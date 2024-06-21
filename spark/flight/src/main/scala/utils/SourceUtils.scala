package utils

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.DoubleType

import java.util.Properties
// 要利用 hadoop分布式 集群系统
object SourceUtils {
//  // 数据库啥的在这里改自己的
//  // 集群之后 要改
//  var url = "jdbc:mysql://localhost:3306/flight?serverTimezone=UTC&useSSL=false"
//  var password: String = "123456"
//  val userName = "root"
//
//  def getSparkSession(): SparkSession = {
//    val spark = SparkSession.builder()
//      .appName("charging-station")
//      .master("local[*]")
//      .getOrCreate()
//    // 日志级别
//    spark.sparkContext.setLogLevel("WARN")
//    spark
//  }
//
//  def getProp(): Properties = {
//    val prop = new java.util.Properties()
//    prop.put("user", SourceUtils.userName)
//    prop.put("password", SourceUtils.password)
//    prop
//  }
//  // 临时视图
//  def getTempView() = {
//    val mysqlTable = "(SELECT * FROM delayedflights) as query"
//    var flightDelay = getSparkSession().read.jdbc(SourceUtils.url, mysqlTable, SourceUtils.getProp())
//
//    val excludeColumns = Set("UniqueCarrier","TailNum","Origin","Dest","CancellationCode","FlightNum")
//    val columnsToCast = flightDelay.columns.filterNot(excludeColumns.contains)
//    flightDelay = columnsToCast.foldLeft(flightDelay) { (currentDF, columnName) =>
//      currentDF.withColumn(columnName, col(columnName).cast(DoubleType))
//    }
//
//    flightDelay.createTempView("flightDelay")
//    flightDelay
//  }
//  def getSparkContext(): SparkContext = {
//    getSparkSession().sparkContext
//  }
}
