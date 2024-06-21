package com.mhdx.spbhello.EFtable.FlightEF;

public class CDelayRateEF {
    private String UniqueCarrier;
    private Integer FlightNub;
    private Double DelayRate;

    public String getUniqueCarrier() {
        return UniqueCarrier;
    }

    public void setUniqueCarrier(String uniqueCarrier) {
        UniqueCarrier = uniqueCarrier;
    }

    public Integer getFlightNub() {
        return FlightNub;
    }

    public void setFlightNub(Integer flightNub) {
        FlightNub = flightNub;
    }

    public Double getDelayRate() {
        return DelayRate;
    }

    public void setDelayRate(Double delayRate) {
        DelayRate = delayRate;
    }
}
