package analyse

import org.apache.spark.SparkContext
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions.{col, spark_partition_id}
import org.apache.spark.sql.types.DoubleType
import utils.SourceUtils

object HiveTest {
  def main(args: Array[String]): Unit = {
//    System.setProperty("HADOOP_USER_NAME", "root")
//    System.setProperty("password","hadoop")
//    val spark: SparkSession = SparkSession.builder().appName("sparksql").master("local[*]")
//      .config("spark.sql.shuffle.partitions", "4")//本次测试时将分区数设置小一点,实际开发中可以根据集群规模调整大小,默认200
//      .config("spark.sql.warehouse.dir", "hdfs://node1:8020/user/hive/warehouse")//指定Hive数据库在HDFS上的位置
//      .config("hive.metastore.uris", "thrift://node1:9083")
//      .enableHiveSupport()//开启对hive语法的支持
//      .getOrCreate()
//
//    val excludeColumns = Set("UniqueCarrier","TailNum","Origin","Dest","CancellationCode","FlightNum") // 请替换为实际的列名
//
//    val df = spark.read
//      .option("header", "true") // 第一行作为header
//      .option("inferSchema", "true") // 自动推断数据类型
////      .csv("data/DelayedFlights.csv")
//      .csv("hdfs://node1:8020/hivedata/DelayedFlights.csv")
//
//    // 选择并转换剩余的列为DoubleType
//    var dfWithDouble = df.select(df.columns.map { columnName =>
//      if (excludeColumns.contains(columnName)) {
//        col(columnName)
//      } else {
//        col(columnName).cast(DoubleType).alias(columnName)
//      }
//    }: _*)
//    // 显示有 null 的列
////    val colsWithNulls = df.columns.filter(columnName => df.filter(col(columnName).isNull).count() > 0)
////    // 输出包含null值的列名
////    println(colsWithNulls.mkString("包含null值的列有: ", ", ", ""))
//
//    val columnsToFill = Seq("ArrTime", "ActualElapsedTime", "CRSElapsedTime", "AirTime", "ArrDelay", "TaxiIn", "TaxiOut", "CarrierDelay","CarrierDelay", "WeatherDelay", "NASDelay", "SecurityDelay", "LateAircraftDelay")
//
//    // 替换null值为0.0
//    dfWithDouble = df.na.fill(0.0, columnsToFill)
//
//    // 切换到数据库
//    spark.sql("USE test")
//
//    // 获取所有表的列表
//    val tables = spark.sql("SHOW TABLES").collect()
//
//    // 删除所有表
//    tables.foreach { table =>
//      val tableName = table.getString(1)
//      spark.sql(s"DROP TABLE IF EXISTS $tableName")
//    }
////    spark.sql("show databases").show()
////    spark.sql("use test")
////    spark.sql("show tables").show()
////    spark.sql("select * from testtable").show()
//    spark.sql("DROP DATABASE IF EXISTS test")
//    spark.sql("CREATE DATABASE IF NOT EXISTS test")
//    spark.sql("use test")
//    dfWithDouble.write.mode("overwrite").saveAsTable("testtable")
//    spark.sql("desc testtable").show(31)
//    val rowCount = spark.sql("SELECT COUNT(*) FROM testtable").collect()(0)(0)
//    println(s"表中有 $rowCount 行")
//
////    dfWithDouble.show()
//    // 检查是否有 数据丢失
////    val rowCount = dfWithDouble.count()
////    println(s"表中有 $rowCount 行")
////    dfWithDouble.createTempView("flightDelay")
//
////    val Depdelay = spark.sql(
////      """
////        |SELECT Origin, MAX(DepDelay) as MaxDepDelay
////        |  FROM flightDelay
////        |  WHERE DepDelay IS NOT NULL
////        |  GROUP BY Origin
////        |  ORDER BY MaxDepDelay DESC
////        |""".stripMargin)
////    Depdelay.show()
////
//    val prop = new java.util.Properties()
//    prop.put("user", "root")
//    prop.put("password", "hadoop")
////
////    Depdelay.write.mode(SaveMode.Overwrite)
////      .jdbc("jdbc:mysql://192.168.88.30:3306/test?serverTimezone=UTC&useSSL=false",
////        "testtable",prop)
//
//
//
//
//    val sc: SparkContext = spark.sparkContext
//    //设置日志级别
//    sc.setLogLevel("WARN")
//    spark.stop()
  }

}
