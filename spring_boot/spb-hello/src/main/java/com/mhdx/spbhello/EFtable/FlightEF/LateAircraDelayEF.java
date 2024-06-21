package com.mhdx.spbhello.EFtable.FlightEF;

public class LateAircraDelayEF {
    private String UniqueCarrier;
    private Integer FlightNum;
    private Double DelayPercentage;

    public String getUniqueCarrier() {
        return UniqueCarrier;
    }

    public void setUniqueCarrier(String uniqueCarrier) {
        UniqueCarrier = uniqueCarrier;
    }

    public Integer getFlightNum() {
        return FlightNum;
    }

    public void setFlightNum(Integer flightNum) {
        FlightNum = flightNum;
    }

    public Double getDelayPercentage() {
        return DelayPercentage;
    }

    public void setDelayPercentage(Double delayPercentage) {
        DelayPercentage = delayPercentage;
    }
}
