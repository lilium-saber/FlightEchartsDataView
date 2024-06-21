package analyse

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{SaveMode, SparkSession}
import org.apache.spark.sql.functions._
import utils.{SourceUtils, flightUtils}

object FlightDemand7 {
  def flightDemand7(): Unit = {
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.OFF)
    val spark = flightUtils.getSparkSession()

//    SourceUtils.getTempView()

    // 统计 每个月 因为天气延误的航班数
    val monthlyWeatherDelayRate = spark.sql(
      """
        |SELECT Year, Month,
        |       SUM(CASE WHEN WeatherDelay > 0 THEN 1 ELSE 0 END) AS NumWeatherDelays,
        |       COUNT(*) AS TotalFlights,
        |       (SUM(CASE WHEN WeatherDelay > 0 THEN 1 ELSE 0 END) / COUNT(*)) * 100 AS WeatherDelayRate
        |FROM flightdelay
        |GROUP BY Year, Month
        |ORDER BY Year, Month
        |""".stripMargin)

    monthlyWeatherDelayRate.show()

    monthlyWeatherDelayRate.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"monthlyWeatherDelayRate",flightUtils.getProp())

    spark.stop()
  }
}
