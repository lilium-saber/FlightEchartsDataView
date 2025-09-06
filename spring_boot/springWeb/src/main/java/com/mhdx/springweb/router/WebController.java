package com.mhdx.springweb.router;

import com.mhdx.springweb.model.Sql.MainDelayReasonRate;
import com.mhdx.springweb.model.Web.AirTimeGet;
import com.mhdx.springweb.model.Web.MonthRateGet;
import com.mhdx.springweb.model.Web.OriginGet;
import io.grpc.ManagedChannelBuilder;
import main.FlightServiceGrpc;
import main.MainDelayReasonRateProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;

import com.mhdx.springweb.service.SqlUse;

@RestController
@RequestMapping("/java")
public class WebController {
    @Autowired
    private SqlUse sqlService;

    @GetMapping("/originmaxdelay")
    public CompletableFuture<List<OriginGet>> getOriginMaxDelay() {
        return sqlService.getTop10MaxDepDelay();
    }

    @GetMapping("/destmaxdelay")
    public CompletableFuture<List<com.mhdx.springweb.model.Sql.maxarrdelay>> getDestMaxDelay() {
        return sqlService.getTop10DestinationDelay();
    }

    @GetMapping("/typedelay")
    public CompletableFuture<List<com.mhdx.springweb.model.Sql.maxdelay>> getMaxdelays() {
        return sqlService.getMaxDelay();
    }

    @GetMapping("/airlongest")
    public CompletableFuture<List<AirTimeGet>> getLongestFlight() {
        return sqlService.getLongestFlight().thenApply(list -> {
            List<AirTimeGet> result = new ArrayList<>();
            for (com.mhdx.springweb.model.Sql.longestflight flight : list) {
                result.add(new AirTimeGet(flight.getFlightNum(), flight.getMaxAirTime().doubleValue()));
            }
            return result;
        });
    }

    @GetMapping("/monthrate")
    public CompletableFuture<List<MonthRateGet>> getMonthRate() {
        return sqlService.getMonthRates().thenApply(list -> {
            List<MonthRateGet> result = new ArrayList<>();
            for (com.mhdx.springweb.model.Sql.monthRate rate : list) {
                result.add(new MonthRateGet(rate.getMonth(), String.format("%.2f", rate.getDelay_rate())));
            }
            return result;
        });
    }

    @GetMapping("/monthrate/{month}")
    public CompletableFuture<List<MonthRateGet>> getMonthRateByMonth(@PathVariable Integer month) {
        return sqlService.getMonthRateByMonth(month).thenApply(list -> {
            List<MonthRateGet> result = new ArrayList<>();
            for (com.mhdx.springweb.model.Sql.monthRate rate : list) {
                result.add(new MonthRateGet(rate.getMonth(), String.format("%.2f", rate.getDelay_rate())));
            }
            return result;
        });
    }

    @GetMapping("/maindelayreasonrate")
    public CompletableFuture<List<MainDelayReasonRate>> getMainDelayReasonRate() {
        var channel = ManagedChannelBuilder.forAddress("localhost", 18080)
                .usePlaintext()
                .build();
        var stub = FlightServiceGrpc.newBlockingStub(channel);
        var request = MainDelayReasonRateProto.Empty.newBuilder().build();
        var response = stub.getMainDelayReasonRates(request);
        channel.shutdown();
        List<MainDelayReasonRate> result = new ArrayList<>();
        for (MainDelayReasonRateProto.MainDelayReasonRate rate : response.getItemsList())
            result.add(new MainDelayReasonRate(rate.getYear(), rate.getMonth(), rate.getNumCarrierDelays(),
                    rate.getNumWeatherDelays(), rate.getNumNASDelays(), rate.getNumSecurityDelays(),
                    rate.getNumLateAircraftDelays(), rate.getTotalDelays(), rate.getCarrierDelayRate(),
                    rate.getWeatherDelayRate(), rate.getNASDelayRate(), rate.getSecurityDelayRate(), rate.getLateAircraftDelayRate()));
        return CompletableFuture.completedFuture(result);
    }
}
