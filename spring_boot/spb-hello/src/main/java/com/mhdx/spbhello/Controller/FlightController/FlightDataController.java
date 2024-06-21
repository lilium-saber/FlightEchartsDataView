package com.mhdx.spbhello.Controller.FlightController;

import com.mhdx.spbhello.EFtable.FlightEF.*;
import com.mhdx.spbhello.Mapper.FlightMapper.FlightMapper;
import com.mhdx.spbhello.scala.FlightScala.DelayedReason;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import scala.collection.JavaConverters;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:5173")
public class FlightDataController {

    @Autowired
    FlightMapper flightMapper;

    DelayedReason delayedReason = new DelayedReason();

    @GetMapping("/flight/typedelay")
    @ResponseBody
    public List<MaxDelayEF> getMaxTypeDelay() {
        return flightMapper.getMaxTypeDelay();
    }

    @GetMapping("/flight/airlongest")
    @ResponseBody
    public List<AirTimeEF> getAirTime() {
        List<AirTimeEF> javalist = flightMapper.getLongestFlight();
        scala.collection.immutable.List<AirTimeEF> scalaList = JavaConverters.asScalaBuffer(javalist).toList();
        scala.collection.immutable.List<AirTimeEF> processedScalaList = delayedReason.airTimeScala(scalaList);
        List<AirTimeEF> processedJavaList = JavaConverters.seqAsJavaList(processedScalaList);
        List<AirTimeEF> list = new ArrayList<>();
        for (AirTimeEF u : processedJavaList) {
            AirTimeEF h = new AirTimeEF();
            h.setFlightNub(u.getFlightNub());
            h.setAirTime(u.getAirTime());
            list.add(h);
        }
        System.out.println(list);
        return list;
    }

    @GetMapping("/flight/monthrate")
    @ResponseBody
    public List<MonthRateEF> getMonthRate() {
        List<MonthRateEF> javalist = flightMapper.getMonthRate();
        scala.collection.immutable.List<MonthRateEF> scalaList = JavaConverters.asScalaBuffer(javalist).toList();
        scala.collection.immutable.List<MonthRateEF> processedScalaList = delayedReason.monthRateScala(scalaList);
        List<MonthRateEF> processedJavaList = JavaConverters.seqAsJavaList(processedScalaList);
        List<MonthRateEF> list = new ArrayList<>();
        for (MonthRateEF u : processedJavaList) {
            MonthRateEF h = new MonthRateEF();
            h.setMonth(u.getMonth());
            h.setTotal_count(u.getTotal_count());
            h.setDelay_count(u.getDelay_count());
            h.setDelay_ratio(u.getDelay_ratio());
            list.add(h);
        }
        return list;
    }

    @GetMapping("/flight/maindelayreasonrate")
    @ResponseBody
    public List<MainDelayReasonRateEF> getMainDelayReasonRate() {
        List<MainDelayReasonRateEF> javalist = flightMapper.getMainDelayReasonRate();
        scala.collection.immutable.List<MainDelayReasonRateEF> scalaList = JavaConverters.asScalaBuffer(javalist).toList();
        scala.collection.immutable.List<MainDelayReasonRateEF> processedScalaList = delayedReason.mainDelayReasonRateScala(scalaList);
        List<MainDelayReasonRateEF> processedJavaList = JavaConverters.seqAsJavaList(processedScalaList);
        List<MainDelayReasonRateEF> list = new ArrayList<>();
        for (MainDelayReasonRateEF u : processedJavaList) {
            MainDelayReasonRateEF h = new MainDelayReasonRateEF();
            h.setYear(u.getYear());
            h.setMonth(u.getMonth());
            h.setNumCarrierDelays(u.getNumCarrierDelays());
            h.setNumWeatherDelays(u.getNumWeatherDelays());
            h.setNumNASDelays(u.getNumNASDelays());
            h.setNumSecurityDelays(u.getNumSecurityDelays());
            h.setNumLateAircraftDelays(u.getNumLateAircraftDelays());
            h.setTotalDelays(u.getTotalDelays());
            h.setCarrierDelayRate(u.getCarrierDelayRate());
            h.setWeatherDelayRate(u.getWeatherDelayRate());
            h.setNASDelayRate(u.getNASDelayRate());
            h.setSecurityDelayRate(u.getSecurityDelayRate());
            h.setLateAircraftDelayRate(u.getLateAircraftDelayRate());
            list.add(h);
        }
        return list;
    }

    @GetMapping("/flight/originmaxdelay")
    @ResponseBody
    public List<MaxOriginDelayEF> getMaxOriginDelay() {
        List<MaxOriginDelayEF> javalist = flightMapper.getMaxOriginDelay();
        scala.collection.immutable.List<MaxOriginDelayEF> scalaList = JavaConverters.asScalaBuffer(javalist).toList();
        scala.collection.immutable.List<MaxOriginDelayEF> processedScalaList = delayedReason.maxDepDelayScala(scalaList);
        List<MaxOriginDelayEF> processedJavaList = JavaConverters.seqAsJavaList(processedScalaList);
        List<MaxOriginDelayEF> list = new ArrayList<>();
        for (MaxOriginDelayEF u : processedJavaList) {
            MaxOriginDelayEF h = new MaxOriginDelayEF();
            h.setOrigin(u.getOrigin());
            h.setMaxDelay(u.getMaxDelay());
            list.add(h);
        }
        return list;
    }

    @GetMapping("/flight/destmaxdelay")
    @ResponseBody
    public List<MaxArrDelayEF> getMaxDestDelay() {
        List<MaxArrDelayEF> javalist = flightMapper.getMaxDestDelay();
        scala.collection.immutable.List<MaxArrDelayEF> scalaList = JavaConverters.asScalaBuffer(javalist).toList();
        scala.collection.immutable.List<MaxArrDelayEF> processedScalaList = delayedReason.maxArrDelayScala(scalaList);
        List<MaxArrDelayEF> processedJavaList = JavaConverters.seqAsJavaList(processedScalaList);
        List<MaxArrDelayEF> list = new ArrayList<>();
        for (MaxArrDelayEF u : processedJavaList) {
            MaxArrDelayEF h = new MaxArrDelayEF();
            h.setDest(u.getDest());
            h.setMaxArrDelay(u.getMaxArrDelay());
            list.add(h);
        }
        return list;
    }

    /*
    @GetMapping("/carrier")
    @ResponseBody
    public List<FlightMapper.CarrierDelay> getCarrierDelay() {
        List<FlightMapper.CarrierDelay> javalist = flightMapper.getCarrierDelay();
        scala.collection.immutable.List<FlightMapper.CarrierDelay> scalaList = JavaConverters.asScalaBuffer(javalist).toList();
        DelayedReason delayedReason = new DelayedReason();
        scala.collection.immutable.List<FlightMapper.CarrierDelay> processedScalaList = delayedReason.CarrierNob(scalaList);
        List<FlightMapper.CarrierDelay> processedJavaList = JavaConverters.seqAsJavaList(processedScalaList);
        List<FlightMapper.CarrierDelay> list = new ArrayList<>();
        for (FlightMapper.CarrierDelay u : processedJavaList) {
            FlightMapper.CarrierDelay h = new FlightMapper.CarrierDelay();
            h.setCarrierDelay(u.getCarrierDelay());
            h.setFlightNum(u.getFlightNum());
            list.add(h);
        }
        return list;
    }
    */

}
