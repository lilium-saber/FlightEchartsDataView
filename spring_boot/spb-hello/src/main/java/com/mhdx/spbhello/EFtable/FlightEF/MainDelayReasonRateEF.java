package com.mhdx.spbhello.EFtable.FlightEF;

public class MainDelayReasonRateEF {
    private Integer Year;
    private Integer Month;
    private Integer NumCarrierDelays;
    private Integer NumWeatherDelays;
    private Integer NumNASDelays;
    private Integer NumSecurityDelays;
    private Integer NumLateAircraftDelays;
    private Integer TotalDelays;
    private Double CarrierDelayRate;
    private Double WeatherDelayRate;
    private Double NASDelayRate;
    private Double SecurityDelayRate;
    private Double LateAircraftDelayRate;

    public Integer getYear() {
        return Year;
    }

    public void setYear(Integer year) {
        Year = year;
    }

    public Integer getMonth() {
        return Month;
    }

    public void setMonth(Integer month) {
        Month = month;
    }

    public Integer getNumCarrierDelays() {
        return NumCarrierDelays;
    }

    public void setNumCarrierDelays(Integer numCarrierDelays) {
        NumCarrierDelays = numCarrierDelays;
    }

    public Integer getNumWeatherDelays() {
        return NumWeatherDelays;
    }

    public void setNumWeatherDelays(Integer numWeatherDelays) {
        NumWeatherDelays = numWeatherDelays;
    }

    public Integer getNumNASDelays() {
        return NumNASDelays;
    }

    public void setNumNASDelays(Integer numNASDelays) {
        NumNASDelays = numNASDelays;
    }

    public Integer getNumSecurityDelays() {
        return NumSecurityDelays;
    }

    public void setNumSecurityDelays(Integer numSecurityDelays) {
        NumSecurityDelays = numSecurityDelays;
    }

    public Integer getNumLateAircraftDelays() {
        return NumLateAircraftDelays;
    }

    public void setNumLateAircraftDelays(Integer numLateAircraftDelays) {
        NumLateAircraftDelays = numLateAircraftDelays;
    }

    public Integer getTotalDelays() {
        return TotalDelays;
    }

    public void setTotalDelays(Integer totalDelays) {
        TotalDelays = totalDelays;
    }

    public Double getCarrierDelayRate() {
        return CarrierDelayRate;
    }

    public void setCarrierDelayRate(Double carrierDelayRate) {
        CarrierDelayRate = carrierDelayRate;
    }

    public Double getWeatherDelayRate() {
        return WeatherDelayRate;
    }

    public void setWeatherDelayRate(Double weatherDelayRate) {
        WeatherDelayRate = weatherDelayRate;
    }

    public Double getNASDelayRate() {
        return NASDelayRate;
    }

    public void setNASDelayRate(Double NASDelayRate) {
        this.NASDelayRate = NASDelayRate;
    }

    public Double getSecurityDelayRate() {
        return SecurityDelayRate;
    }

    public void setSecurityDelayRate(Double securityDelayRate) {
        SecurityDelayRate = securityDelayRate;
    }

    public Double getLateAircraftDelayRate() {
        return LateAircraftDelayRate;
    }

    public void setLateAircraftDelayRate(Double lateAircraftDelayRate) {
        LateAircraftDelayRate = lateAircraftDelayRate;
    }

}
