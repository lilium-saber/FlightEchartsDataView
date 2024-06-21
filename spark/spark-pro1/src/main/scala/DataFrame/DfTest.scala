package DataFrame

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext
import org.apache.spark.sql.functions.{col, count, sum, when}
import org.apache.spark.sql.{DataFrame, SparkSession}

import java.sql.{Connection, ResultSet}

object DfTest {

	case class Person(id: Int, name: String, org: String)
	case class FlightData(id: Int, year: Int, month: Int, dayofmonth: Int, dayofweek: Int, deptime: Double, crsdeptime: Double, arrtime: Double, crsarrtime: Double, uniquecarrier: String, flightnum: Int, tailnum: String, actualelapsedtime: Double, crselapsedtime: Double, airtime: Double, arrdelay: Double, depdelay: Double, origin: String, dest: String, distance: Double, taxiin: Double, taxiout: Double, cancelled: Double, cancellationcode: String, diverted: Double, carrierdelay: Double, weatherdelay: Double, nasdelay: Double, securitydelay: Double, lateaircraftdelay: Double)

	def main(args: Array[String]): Unit = {
		val rootLogger = Logger.getRootLogger
		rootLogger.setLevel(Level.OFF)
		val conf = new org.apache.spark.SparkConf().setAppName("DfTest").setMaster("local")
		val sc: SparkContext = new SparkContext(conf)

		val ss: SparkSession = SparkSession
			.builder()
			.appName("DfTest")
			.master("local[2]")
			.getOrCreate()


		val jdbcUrl = "jdbc:hive2://centos:10000/flight"
		val username = "root"
		val password = ""

		val driverName = "org.apache.hive.jdbc.HiveDriver"

		var connection: Connection = null
		var stmt: java.sql.Statement = null
		var re: ResultSet = null
		import ss.implicits._

		val file: DataFrame = sc.textFile("data/dflight.csv")
			.map(_.split("\u0001"))
			.map(x => x.map(s => if (s == "\\N") "0" else s)) // replace "\N" with "0"
			.map(x => FlightData(x(0).toInt, x(1).toInt, x(2).toInt, x(3).toInt, x(4).toInt, x(5).toDouble, x(6).toDouble, x(7).toDouble, x(8).toDouble, x(9), x(10).toInt, x(11), x(12).toDouble, x(13).toDouble, x(14).toDouble, x(15).toDouble, x(16).toDouble, x(17), x(18), x(19).toDouble, x(20).toDouble, x(21).toDouble, x(22).toDouble, x(23), x(24).toDouble, x(25).toDouble, x(26).toDouble, x(27).toDouble, x(28).toDouble, x(29).toDouble))
			.toDF()

		var handlefile: DataFrame = file.select("month", "carrierdelay", "weatherdelay", "nasdelay", "securitydelay", "lateaircraftdelay")
		val handlefileWithZeroIndicator = handlefile.withColumn(
			"hasZero",
			when(
				col("carrierdelay") === 0 ||
					col("weatherdelay") === 0 ||
					col("nasdelay") === 0 ||
					col("securitydelay") === 0 ||
					col("lateaircraftdelay") === 0,
				1
			).otherwise(0)
		)

		// Calculate the proportion of rows with 0 for each month
		val zeroProportionPerMonth = handlefileWithZeroIndicator
			.groupBy("month")
			.agg(
				(sum("hasZero") / count("*")).alias("zero_proportion")
			)

		zeroProportionPerMonth.show()



		try{
			Class.forName(driverName)
			connection = java.sql.DriverManager.getConnection(jdbcUrl, username, password)
			stmt = connection.createStatement()

			/*
			val que = "select * from userdata"
			re = stmt.executeQuery(que)
			while (re.next()){
				println(re.getInt("id") + " " + re.getString("name"))
			}

			 */

		}catch {
			case e: Exception => e.printStackTrace()
		}finally {
			if (stmt != null) stmt.close()
			if (connection != null) connection.close()
			if(re != null) re.close()
		}

		/*
		//dml风格
		//1 直接读文件
		val df2: DataFrame = sc.textFile("data/holo.csv")
			.map(_.split(","))
			.map(x => (x(0).toInt, x(1), x(2)))
			.toDF("id", "name", "org")
		df2.show(50)

		println("-" * 50)
		//使用样例类则不用指定列名 RDD[Person]
		val df3: DataFrame = sc.textFile("data/holo.csv")
			.map(_.split(","))
			.map(x => Person(x(0).toInt, x(1), x(2)))
			.toDF()
		df3.select("name", "org").show(50)
		df3.filter(df3("org") === "myth").show(50)//===等于，!==不等于
		df3.groupBy("org").count().sort("count").show(50)

		//sql风格
		println("-" * 50)
		val  df4: DataFrame = df3
		df4.createOrReplaceTempView("person")
		ss.sql("SELECT * FROM ( " +
		    "SELECT org, count(org) as people " +
		    "FROM person " +
		    "GROUP BY org " +
			") WHERE org " +
				"RLIKE '.*a.*'")//注意空格, 分行分割语句
			.sort("people")
			.show(50)

		val df5 = sc.textFile("data/words.txt")
			.flatMap(_.split(" "))
			.map((_, 1))
			.toDF("word", "count")

		df5.groupBy("word").count().sort("count").show(50)

		//使用dataset
		val ds = df3
		val ds1: Dataset[Person] = ds.as[Person]
		ds1.show(50)

		var ds2 = ds1.toDF()
		ds2.show(50)
		*/




		sc.stop()
		ss.close()





	}

}
