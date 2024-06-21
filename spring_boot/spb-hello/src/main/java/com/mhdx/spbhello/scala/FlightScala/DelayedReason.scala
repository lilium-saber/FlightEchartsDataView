package com.mhdx.spbhello.scala.FlightScala

import com.mhdx.spbhello.EFtable.FlightEF.{AirTimeEF, MainDelayReasonRateEF, MaxArrDelayEF, MaxOriginDelayEF, MonthRateEF}

class DelayedReason {
/*
	def typeDelayScala(data: List[MaxDelayEF]): List[MaxDelayEF] = {
		return data.sortWith(_.getMaxDelay > _.getMaxDelay)
	}
*/
	def airTimeScala(data: List[AirTimeEF]): List[AirTimeEF] = {
		return data.sortWith(_.getAirTime > _.getAirTime).take(10)
	}

	def maxDepDelayScala(data: List[MaxOriginDelayEF]): List[MaxOriginDelayEF] = {
		return data.sortWith(_.getMaxDelay > _.getMaxDelay).take(10)
	}

	def maxArrDelayScala(data: List[MaxArrDelayEF]): List[MaxArrDelayEF] = {
		return data.sortWith(_.getMaxArrDelay > _.getMaxArrDelay).take(10)
	}

	def adjustDelayRatio(monthRate: MonthRateEF): MonthRateEF = {
		val newDelayRatio = BigDecimal(monthRate.getDelay_ratio * 100).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
		val newMonthRate = new MonthRateEF()
		newMonthRate.setMonth(monthRate.getMonth)
		newMonthRate.setTotal_count(monthRate.getTotal_count)
		newMonthRate.setDelay_count(monthRate.getDelay_count)
		newMonthRate.setDelay_ratio(newDelayRatio)
		return newMonthRate
	}
	def monthRateScala(data: List[MonthRateEF]): List[MonthRateEF] = {
		return data.map(adjustDelayRatio)
	}

	def adjustDelayMonthRate(mainDelayReasonRateEF: MainDelayReasonRateEF): MainDelayReasonRateEF = {
		val newCarrierDelayRate = BigDecimal(mainDelayReasonRateEF.getCarrierDelayRate * 1).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
		val newWeatherDelayRate = BigDecimal(mainDelayReasonRateEF.getWeatherDelayRate * 1).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
		val newNASDelayRate = BigDecimal(mainDelayReasonRateEF.getNASDelayRate * 1).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
		val newSecurityDelayRate = BigDecimal(mainDelayReasonRateEF.getSecurityDelayRate * 1).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
		val newLateAircraftDelayRate = BigDecimal(mainDelayReasonRateEF.getLateAircraftDelayRate * 1).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
		val newMainDelayReasonRateEF = new MainDelayReasonRateEF()
		newMainDelayReasonRateEF.setMonth(mainDelayReasonRateEF.getMonth)
		newMainDelayReasonRateEF.setCarrierDelayRate(newCarrierDelayRate)
		newMainDelayReasonRateEF.setWeatherDelayRate(newWeatherDelayRate)
		newMainDelayReasonRateEF.setNASDelayRate(newNASDelayRate)
		newMainDelayReasonRateEF.setSecurityDelayRate(newSecurityDelayRate)
		newMainDelayReasonRateEF.setLateAircraftDelayRate(newLateAircraftDelayRate)
		newMainDelayReasonRateEF.setYear(mainDelayReasonRateEF.getYear)
		newMainDelayReasonRateEF.setNumCarrierDelays(mainDelayReasonRateEF.getNumCarrierDelays)
		newMainDelayReasonRateEF.setNumWeatherDelays(mainDelayReasonRateEF.getNumWeatherDelays)
		newMainDelayReasonRateEF.setNumNASDelays(mainDelayReasonRateEF.getNumNASDelays)
		newMainDelayReasonRateEF.setNumSecurityDelays(mainDelayReasonRateEF.getNumSecurityDelays)
		newMainDelayReasonRateEF.setNumLateAircraftDelays(mainDelayReasonRateEF.getNumLateAircraftDelays)
		newMainDelayReasonRateEF.setTotalDelays(mainDelayReasonRateEF.getTotalDelays)
		return newMainDelayReasonRateEF
	}
	def mainDelayReasonRateScala(data: List[MainDelayReasonRateEF]): List[MainDelayReasonRateEF] = {
		return data.map(adjustDelayMonthRate)
	}


}
