package analyse

import org.apache.spark.sql.functions.col
import org.apache.spark.sql.{DataFrame, SaveMode}
import utils.{SourceUtils, flightUtils}

object FlightDemand1 {
  def flightDemand1(): Unit = {
    val spark = flightUtils.getSparkSession()

    // 读取MySQL表数据 并设置了一个临时视图 flightDelay
//    SourceUtils.getTempView()

    // 查询 每种原因的 最大延误时间
    val delayReasons = Seq("CarrierDelay", "WeatherDelay", "NASDelay", "SecurityDelay", "LateAircraftDelay")
    val delayReasonQueries = delayReasons.map { reason =>
      s"SELECT '$reason' as DelayType, MAX($reason) as MaxDelay FROM flightDelay WHERE $reason IS NOT NULL"
    }
    val combinedQuery = delayReasonQueries.mkString(" UNION ALL ")

    spark.sql(combinedQuery).show()
    // 将查询的结果 放入一个 Dataframe 中 方便使用 spark 将结果写入表中
    var df: DataFrame = spark.sql(combinedQuery)

    // 写到 MySQL数据库中
    df.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"MaxDelay",flightUtils.getProp())
    spark.stop()
  }

}