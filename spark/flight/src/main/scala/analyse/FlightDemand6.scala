package analyse

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SaveMode
import utils.{SourceUtils, flightUtils}

object FlightDemand6 {
  def flightDemand6(): Unit = {
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.OFF)
    val spark = flightUtils.getSparkSession()

    // 创建临时视图
//    SourceUtils.getTempView()
    // 统计 每个月的延误率
    val monthlyDelayRate = spark.sql(
      """
        |SELECT Year, Month,
        |       SUM(CASE WHEN CarrierDelay > 0 OR WeatherDelay > 0 OR NASDelay > 0 OR SecurityDelay > 0 OR LateAircraftDelay > 0 THEN 1 ELSE 0 END) AS NumDelays,
        |       COUNT(*) AS TotalFlights,
        |       (SUM(CASE WHEN CarrierDelay > 0 OR WeatherDelay > 0 OR NASDelay > 0 OR SecurityDelay > 0 OR LateAircraftDelay > 0 THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS DelayRate
        |FROM flightdelay
        |GROUP BY Year, Month
        |ORDER BY Year, Month
        |""".stripMargin)

    monthlyDelayRate.show()

    monthlyDelayRate.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"monthlyDelayRate",flightUtils.getProp())

    spark.stop()
  }
}
