package rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, SparkSession}

object RddDemon01 {
	def main(args: Array[String]): Unit = {
		/*
		val conf: SparkConf = new SparkConf().setAppName("RddDemon01").setMaster("local")
		val sc = new org.apache.spark.SparkContext(conf)//sc是spark的入口，spark的所有功能都是通过sc调用的
		sc.setLogLevel("ERROR")

		var rdd: RDD[String] = sc.textFile("data/words.txt")
		var rdd1: RDD[String] = rdd.filter(_.contains("Hadoop"))
		println(rdd1.collect().toBuffer)

		var rdd2: RDD[String] = rdd.flatMap(_.split(" "))
		println(rdd2.collect().toBuffer)

		var rdd3: RDD[(String, Int)] = rdd
			.flatMap(_.split(" "))
			.map((_, 1))
			.reduceByKey(_ + _)
		println(rdd3.collect().filter(_._2 <= 3).map(x => x._1).toBuffer)//值大于3
		println(rdd3.count())
		sc.stop()

		 */

		//df to rdd
		val spark: SparkSession = SparkSession.builder().appName("RddDemon01").master("local").getOrCreate()
		import spark.implicits._
		var df: DataFrame = spark
			.read
			.option("header", "false")
			.option("inferSchema", "true")
			.csv("data/holo.csv")

		if(df.columns.length >= 3) {
			var rdd: RDD[(Int, String, String)] = df
				.rdd
				.map(row =>
					(row.getInt(0),
						row.getString(1),
						row.get(2).toString))
			var dfF: DataFrame = rdd.toDF("nb", "name", "org")
			dfF.show(50)
			println(dfF.count().toInt)
		} else {
			println("The DataFrame does not have enough columns")
		}


	}
}
