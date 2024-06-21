import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD


object WordCount {
	def main(args: Array[String]): Unit = {

		var conf = new SparkConf().setAppName("WordCount").setMaster("local")
		var sc = new SparkContext(conf)//用于连接spark集群的入口
		sc.setLogLevel("ERROR")
		var textFile: RDD[String] = sc.textFile(args(0))//读取文件
		var counts: RDD[(String, Int)] = textFile
			.flatMap(_.split(" "))
			.map((_, 1))
			.reduceByKey(_ + _)
		counts.saveAsTextFile(args(1))
		sc.stop()
		//counts.collect().foreach(println)
		
	}
}
