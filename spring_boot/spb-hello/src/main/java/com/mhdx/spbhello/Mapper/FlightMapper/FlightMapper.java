package com.mhdx.spbhello.Mapper.FlightMapper;

import com.mhdx.spbhello.EFtable.FlightEF.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FlightMapper {

    @Select("SELECT * from flight.maxdelay")
    public List<MaxDelayEF> getMaxTypeDelay();

    @Select("SELECT FlightNum as FlightNub, MaxAirTime as AirTime from flight.longestflight limit 10")
    public List<AirTimeEF> getLongestFlight();

    @Select("SELECT Origin as Origin, MaxDepDelay as MaxDelay from flight.maxdepdelay")
    public List<MaxOriginDelayEF> getMaxOriginDelay();

    @Select("SELECT Dest as Dest, MaxArrDelay as MaxArrDelay from flight.maxarrdelay")
    public List<MaxArrDelayEF> getMaxDestDelay();

    @Select("SELECT Month as Month, total_count as total_count, delay_count as delay_count, delay_ratio as delay_ratio from flight.monthdelayrate")
    public List<MonthRateEF> getMonthRate();

    @Select("SELECT Year as Year, Month as Month, NumCarrierDelays as NumCarrierDelays, NumWeatherDelays as NumWeatherDelays, NumNASDelays as NumNASDelays, NumSecurityDelays as NumSecurityDelays, NumLateAircraftDelays as NumLateAircraftDelays, TotalDelays as TotalDelays, CarrierDelayRate as CarrierDelayRate, WeatherDelayRate as WeatherDelayRate, NASDelayRate as NASDelayRate, SecurityDelayRate as SecurityDelayRate, LateAircraftDelayRate as LateAircraftDelayRate from flight.mainDelayReasonRate")
    public List<MainDelayReasonRateEF> getMainDelayReasonRate();
}
