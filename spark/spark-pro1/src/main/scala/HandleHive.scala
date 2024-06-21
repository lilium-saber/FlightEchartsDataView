import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

import java.sql.{Connection, ResultSet}


object HandleHive {
	def main(args: Array[String]): Unit = {
		val jdbcUrl = "jdbc:hive2://centos:10000/flight"
		val username = "root"
		val password = ""

		val driverName = "org.apache.hive.jdbc.HiveDriver"

		var connection: Connection = null
		var stmt: java.sql.Statement = null
		var re: ResultSet = null
		val rootLogger = Logger.getRootLogger
		rootLogger.setLevel(Level.OFF)

		val conf = new SparkConf().setAppName("HandleHive").setMaster("local")
		val sc: SparkContext = new SparkContext(conf)

		val ss: SparkSession = SparkSession
			.builder()
			.appName("HandleHive")
			.master("local[2]")
			.getOrCreate()
		import ss.implicits._





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

	}
}
