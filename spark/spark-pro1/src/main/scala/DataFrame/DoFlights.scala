package DataFrame

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object DoFlights {
	def main(args: Array[String]): Unit = {
		val rootLogger = Logger.getRootLogger
		rootLogger.setLevel(Level.OFF)

		System.setProperty("root", "")
		val conf = new SparkConf().setAppName("DoFlights").setMaster("local")
		val spark = SparkSession.builder().config(conf)
			.config("spark.sql.warehouse.dir", "hdfs://centos:8020/user/hive/warehouse")
			.config("hive.metastore.uris", "thrift://centos:9083")
			.enableHiveSupport().getOrCreate()

		spark.sql("use flight")

		val df = spark.read.option("header", "true")
			.csv("./src/main/data/DelayedFlights.csv")
		val df2 = df.select("Month", "CarrierDelay", "WeatherDelay", "NASDelay", "SecurityDelay", "LateAircraftDelay")
		val df3 = df2.withColumn("Month", df2("Month").cast("int"))
			.withColumn("CarrierDelay", df2("CarrierDelay").cast("double"))
			.withColumn("WeatherDelay", df2("WeatherDelay").cast("double"))
			.withColumn("NASDelay", df2("NASDelay").cast("double"))
			.withColumn("SecurityDelay", df2("SecurityDelay").cast("double"))
			.withColumn("LateAircraftDelay", df2("LateAircraftDelay").cast("double"))
		val df4 = df3.na.fill(0.0, Seq("CarrierDelay", "WeatherDelay", "NASDelay", "SecurityDelay", "LateAircraftDelay"))
		val month_count = df4.filter("Month > 0").groupBy("Month").count().orderBy("Month")
		val month_delay_count = df4.filter("CarrierDelay > 0 or WeatherDelay > 0 or NASDelay > 0 or SecurityDelay > 0 or LateAircraftDelay > 0")
			.groupBy("Month").count().orderBy("Month")

		month_count.createOrReplaceTempView("month_count_view")
		month_delay_count.createOrReplaceTempView("month_delay_count_view")
		val joined_df = spark.sql("""
  SELECT a.Month, a.count as total_count, b.count as delay_count
  FROM month_count_view a
  JOIN month_delay_count_view b
  ON a.Month = b.Month
""")
		val result_df = joined_df.withColumn("delay_ratio", joined_df("delay_count") / joined_df("total_count")).orderBy("Month")
		result_df.show()
		// 将DataFrame保存为Hive表
		// result_df.write.mode("overwrite").saveAsTable("flight.delay_ratio")
		// 将DataFrame保存为CSV文件
		//var
		result_df.coalesce(1).write.option("header", "true").csv("./src/main/data/DelayedFlights_ratio.csv")


		spark.stop()
	}
}
