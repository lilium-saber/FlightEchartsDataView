package analyse

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SaveMode
import org.apache.spark.sql.functions.col
import utils.{SourceUtils, flightUtils}

object FlightDemand3 {
  def flightDemand3(): Unit = {
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.OFF)

    val spark = flightUtils.getSparkSession()

//    SourceUtils.getTempView()
    // 飞行时间最长的航班
    val longestFlight = spark.sql(
      """
        |SELECT FlightNum, MAX(AirTime) as MaxAirTime
        | FROM flightdelay
        | GROUP BY FlightNum
        | ORDER BY MaxAirTime DESC
        |""".stripMargin)
    longestFlight.show()

    longestFlight.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"MaxAirTime",flightUtils.getProp())
    spark.stop()
  }

}
