package analyse

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SaveMode}
import org.apache.spark.sql.functions.col
import utils.{SourceUtils, flightUtils}

object FlightDemand2 {
  def flightDemand2(): Unit = {
    val spark = flightUtils.getSparkSession()

    val rootLogger = Logger.getRootLogger
    rootLogger.setLevel(Level.OFF)
//    SourceUtils.getTempView()

    // 每个起始机场的 最高起飞延误时间
    val Depdelay = spark.sql(
      """
        |SELECT Origin, MAX(DepDelay) as MaxDepDelay
        |  FROM flightdelay
        |  WHERE DepDelay IS NOT NULL
        |  GROUP BY Origin
        |  ORDER BY MaxDepDelay DESC
        |  LIMIT 10
        |""".stripMargin)
    Depdelay.show()

    // 每个目的地机场的 最高到达延误时间
    val ArrDelay = spark.sql(
      """
        |SELECT Dest, MAX(ArrDelay) as MaxArrDelay
        |  FROM flightdelay
        |  WHERE ArrDelay IS NOT NULL
        |  GROUP BY Dest
        |  ORDER BY MaxArrDelay DESC
        |""".stripMargin)
    ArrDelay.show()

    // 每个起始机场的 平均起飞延误时间
    val avgDepDelay = spark.sql(
      """
        |SELECT Origin, AVG(DepDelay) as AvgDepDelay
        |FROM flightdelay
        |WHERE DepDelay IS NOT NULL
        |GROUP BY Origin
        |""".stripMargin)
    avgDepDelay.createTempView("avgDepDelay")

    val To10avgDepDelay = spark.sql(
      """
        |SELECT a.Origin, a.AvgDepDelay
        |FROM avgDepDelay a
        |JOIN (SELECT Origin, MAX(DepDelay) as MaxDepDelay
        |      FROM flightdelay
        |      WHERE DepDelay IS NOT NULL
        |      GROUP BY Origin
        |      ORDER BY MaxDepDelay DESC
        |      LIMIT 10) d ON a.Origin = d.Origin
        |ORDER BY d.MaxDepDelay DESC
        |""".stripMargin)
    To10avgDepDelay.show()



    // 计算每个目的地机场的 平均到达延误时间
    val avgArrDelay = spark.sql(
      """
        |SELECT Dest, AVG(ArrDelay) as AvgArrDelay
        |FROM flightdelay
        |WHERE ArrDelay IS NOT NULL
        |GROUP BY Dest
        |ORDER BY AvgArrDelay DESC
        |""".stripMargin)
    avgArrDelay.createTempView("avgArrDelay")


    val To10avgArrpDelay = spark.sql(
      """
        |SELECT a.Dest, a.AvgArrDelay
        |FROM avgArrDelay a
        |JOIN (SELECT Dest, MAX(ArrDelay) as MaxArrDelay
        |      FROM flightdelay
        |      WHERE ArrDelay IS NOT NULL
        |      GROUP BY Dest
        |      ORDER BY MaxArrDelay DESC
        |      LIMIT 10) d ON a.Dest = d.Dest
        |ORDER BY d.MaxArrDelay DESC
        |""".stripMargin)
    To10avgArrpDelay.show()

    // 显示结果
//    avgDepDelay.show()
//    avgArrDelay.show()




    ArrDelay.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"MaxArrDelay",flightUtils.getProp())

    Depdelay.write.mode(SaveMode.Overwrite)
          .jdbc(flightUtils.url,"MaxDepDelay",flightUtils.getProp())
    To10avgDepDelay.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"To10avgDepDelay",flightUtils.getProp())
    To10avgArrpDelay.write.mode(SaveMode.Overwrite)
      .jdbc(flightUtils.url,"To10avgArrpDelay",flightUtils.getProp())

    spark.stop()
  }

}
