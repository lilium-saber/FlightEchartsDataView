package com.mhdx.springweb.model.Web;

public class AirTimeGet {
    private Integer FlightNub;
    private Double AirTime;

    public AirTimeGet(Integer FlightNub, Double AirTime) {
        this.FlightNub = FlightNub;
        this.AirTime = AirTime;
    }
    public Integer getFlightNub() {
        return FlightNub;
    }
    public Double getAirTime() {
        return AirTime;
    }
}
