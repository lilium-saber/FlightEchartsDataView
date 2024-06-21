package analyse

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SaveMode}
import utils.{SourceUtils, flightUtils}

object FlightDemand5 {
  def flightDemand5(): Unit = {

    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.OFF)
    val spark = flightUtils.getSparkSession()

//    SourceUtils.getTempView()

    // 每种 延误原因的平均延误时间
    // 延误原因
    val delayReasons = Seq("CarrierDelay", "LateAircraftDelay", "NASDelay", "WeatherDelay", "SecurityDelay")

    // 为每种延误原因生成查询语句
    val delayReasonQueries = delayReasons.map { reason =>
      s"SELECT '$reason' as DelayType, AVG($reason) as AvgDelay FROM flightdelay WHERE $reason IS NOT NULL"
    }

    // 合并查询语句
    val combinedQuery = delayReasonQueries.mkString(" UNION ALL ")

    // 执行查询
    val avgDelayDF: DataFrame = spark.sql(combinedQuery)

    // 显示结果
    avgDelayDF.show()

    // 将结果写入MySQL数据库
    avgDelayDF.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url, "AvgDelay", flightUtils.getProp())

    spark.stop()
  }
}
