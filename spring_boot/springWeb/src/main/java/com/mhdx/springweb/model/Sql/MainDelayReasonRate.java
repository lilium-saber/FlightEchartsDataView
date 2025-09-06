package com.mhdx.springweb.model.Sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

public class MainDelayReasonRate {
    @Getter
    private Integer Year;
    @Getter
    private Integer Month;
    @Getter
    private Integer numCarrierDelays;
    @Getter
    private Integer numWeatherDelays;
    @Getter
    private Integer numNASDelays;
    @Getter
    private Integer numSecurityDelays;
    @Getter
    private Integer numLateAircraftDelays;
    @Getter
    private Integer totalDelays;
    @Getter
    private Double carrierDelayRate;
    @Getter
    private Double weatherDelayRate;
    @Getter
    private Double nASDelayRate;
    @Getter
    private Double securityDelayRate;
    @Getter
    private Double lateAircraftDelayRate;

    public MainDelayReasonRate(Integer Year, Integer Month, Integer numCarrierDelays, Integer numWeatherDelays,
                               Integer numNASDelays, Integer numSecurityDelays, Integer numLateAircraftDelays, Integer totalDelays,
                               Double carrierDelayRate, Double weatherDelayRate, Double nASDelayRate, Double securityDelayRate,
                               Double lateAircraftDelayRate) {
        this.Year = Year;
        this.Month = Month;
        this.numCarrierDelays = numCarrierDelays;
        this.numWeatherDelays = numWeatherDelays;
        this.numNASDelays = numNASDelays;
        this.numSecurityDelays = numSecurityDelays;
        this.numLateAircraftDelays = numLateAircraftDelays;
        this.totalDelays = totalDelays;
        this.carrierDelayRate = carrierDelayRate;
        this.weatherDelayRate = weatherDelayRate;
        this.nASDelayRate = nASDelayRate;
        this.securityDelayRate = securityDelayRate;
        this.lateAircraftDelayRate = lateAircraftDelayRate;
    }
}

