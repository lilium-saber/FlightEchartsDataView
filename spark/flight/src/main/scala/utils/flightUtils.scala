package utils
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.DoubleType

import java.util.Properties

object flightUtils {

  var mysql_username = "root"
  var mysql_password = "hadoop"
  // 实现创建此数据库
  var mysql_database = "flightresult"
  var mysql_post = "192.168.88.30"
  var url = s"jdbc:mysql://$mysql_post:3306/$mysql_database?serverTimezone=UTC&useSSL=false"

  def getSparkSession(): SparkSession ={
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.OFF)

    System.setProperty("HADOOP_USER_NAME", "root")
    System.setProperty("password","hadoop")
    //TODO 0.准备环境---需要增加参数配置和开启hivesql语法支持
    val spark: SparkSession = SparkSession.builder().appName("sparksql").master("local[*]")
      .config("spark.sql.shuffle.partitions", "4")//本次测试时将分区数设置小一点,实际开发中可以根据集群规模调整大小,默认200
      .config("spark.sql.warehouse.dir", "hdfs://192.168.88.30:8020/user/hive/warehouse")//指定Hive数据库在HDFS上的位置
      .config("hive.metastore.uris", "thrift://192.168.88.30:9083")
      .enableHiveSupport()//开启对hive语法的支持
      .getOrCreate()

    spark.sql("use flightdata")
    spark
  }



  // 将 hdfs 的csv文件加载到hive仓库的表中
  def writeCsvToTable() = {
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.OFF)

    val spark = flightUtils.getSparkSession()
    val excludeColumns = Set("UniqueCarrier","TailNum","Origin","Dest","CancellationCode","FlightNum") // 请替换为实际的列名

    val df = spark.read
      .option("header", "true") // 第一行作为header
      .option("inferSchema", "true") // 自动推断数据类型
      //      .csv("data/DelayedFlights.csv")
      .csv("hdfs://192.168.88.30:8020/hivedata/DelayedFlights.csv")

    // 选择并转换剩余的列为DoubleType
    var dfWithDouble = df.select(df.columns.map { columnName =>
      if (excludeColumns.contains(columnName)) {
        col(columnName)
      } else {
        col(columnName).cast(DoubleType).alias(columnName)
      }
    }: _*)

    // 替换null值为0.0
    val columnsToFill = Seq("ArrTime", "ActualElapsedTime", "CRSElapsedTime", "AirTime", "ArrDelay", "TaxiIn", "TaxiOut", "CarrierDelay","CarrierDelay", "WeatherDelay", "NASDelay", "SecurityDelay", "LateAircraftDelay")
    dfWithDouble = df.na.fill(0.0, columnsToFill)

    // 切换到数据库
    spark.sql("CREATE DATABASE IF NOT EXISTS flightdata")
    spark.sql("use flightdata")
    dfWithDouble.write.mode("overwrite").saveAsTable("flightDelay")
  }

  def getProp(): Properties = {
    val prop = new java.util.Properties()
    prop.put("user", mysql_username)
    prop.put("password", mysql_password)
    prop
  }


}
