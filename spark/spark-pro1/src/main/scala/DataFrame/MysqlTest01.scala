package DataFrame

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

object MysqlTest01 {
	def main(args: Array[String]): Unit = {
		val rootLogger = Logger.getRootLogger
		rootLogger.setLevel(Level.OFF)

		val conf: SparkConf = new SparkConf().setAppName("MysqlDemon").setMaster("local")
		val sc: SparkContext = new SparkContext(conf)
		val spark: SparkSession = SparkSession.builder().appName("MysqlDemon").master("local[2]").getOrCreate()
		import spark.implicits._

		val mysql_url = "jdbc:mysql://centos:3306/spark"
		val prop = new java.util.Properties()
		prop.setProperty("user", "root")
		prop.setProperty("password", "hadoop")
		//读取mysql
		val mysql_table_person: DataFrame = spark.read.jdbc(mysql_url, "person", prop)
		mysql_table_person.printSchema()
		mysql_table_person.show(50)
		//写入mysql
		val mysql_w = sc.textFile("data/holo.csv")
			.map(_.split(","))
			.map(x => (x(0).toInt, x(1), x(2)))
		val input_holo1 = mysql_w.toDF("id", "name", "org").drop("org")
		val input_holo2 = input_holo1.withColumn("age", input_holo1("id") + 10)
		//append追加模式, overwrite覆盖模式, ignore忽略模式, error默认模式
		input_holo2.write.mode("overwrite").jdbc(mysql_url, "person", prop)

		sc.stop()
		spark.stop()


	}
}
