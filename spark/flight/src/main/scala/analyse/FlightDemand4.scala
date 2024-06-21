package analyse

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SaveMode
import utils.{SourceUtils, flightUtils}

object FlightDemand4 {
  def flightDemand4(): Unit = {
    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.OFF)

    val spark = flightUtils.getSparkSession()

//    SourceUtils.getTempView()
    // 统计 航空公司原因 延误 的航班的延误率
    val carrierDelayRate = spark.sql(
      """
        |WITH FlightCounts AS (
        |    SELECT UniqueCarrier, FlightNum, COUNT(*) AS TotalFlights
        |    FROM flightdelay
        |    GROUP BY UniqueCarrier, FlightNum
        |    HAVING COUNT(*) > 50
        |  ),
        |  DelayedFlights AS (
        |    SELECT f.UniqueCarrier, f.FlightNum, COUNT(*) AS DelayedFlights
        |    FROM flightdelay f
        |    JOIN FlightCounts fc ON f.UniqueCarrier = fc.UniqueCarrier AND f.FlightNum = fc.FlightNum
        |    WHERE f.CarrierDelay > 0
        |    GROUP BY f.UniqueCarrier, f.FlightNum
        |  )
        |  SELECT fc.UniqueCarrier, fc.FlightNum,
        |         (df.DelayedFlights / fc.TotalFlights) * 100 AS DelayPercentage
        |  FROM FlightCounts fc
        |  JOIN DelayedFlights df ON fc.UniqueCarrier = df.UniqueCarrier AND fc.FlightNum = df.FlightNum
        |  ORDER BY DelayPercentage DESC
        |""".stripMargin)

    // 统计 前序航班原因而延误 的航班的延误率
    val lateAircraftDelayRate = spark.sql(
      """
        |WITH FlightCounts AS (
        |    SELECT UniqueCarrier, FlightNum, COUNT(*) AS TotalFlights
        |    FROM flightdelay
        |    GROUP BY UniqueCarrier, FlightNum
        |    HAVING COUNT(*) > 50
        |  ),
        |  DelayedFlights AS (
        |    SELECT f.UniqueCarrier, f.FlightNum, COUNT(*) AS DelayedFlights
        |    FROM flightdelay f
        |    JOIN FlightCounts fc ON f.UniqueCarrier = fc.UniqueCarrier AND f.FlightNum = fc.FlightNum
        |    WHERE f.LateAircraftDelay > 0
        |    GROUP BY f.UniqueCarrier, f.FlightNum
        |  )
        |  SELECT fc.UniqueCarrier, fc.FlightNum,
        |         (df.DelayedFlights / fc.TotalFlights) * 100 AS DelayPercentage
        |  FROM FlightCounts fc
        |  JOIN DelayedFlights df ON fc.UniqueCarrier = df.UniqueCarrier AND fc.FlightNum = df.FlightNum
        |  ORDER BY DelayPercentage DESC
        |  LIMIT 10
        |""".stripMargin)

    carrierDelayRate.show()
    lateAircraftDelayRate.show()

    carrierDelayRate.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"carrierDelayRate",flightUtils.getProp())
    lateAircraftDelayRate.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"lateAircraftDelayRate",flightUtils.getProp())
    spark.stop()

  }

}
