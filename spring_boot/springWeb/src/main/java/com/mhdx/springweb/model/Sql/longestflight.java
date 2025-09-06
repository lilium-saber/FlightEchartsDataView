package com.mhdx.springweb.model.Sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.criteria.CriteriaBuilder;

public class longestflight {
    private Integer FlightNum;
    private Integer MaxAirTime;

    public longestflight(Integer FlightNum, Integer MaxAirTime) {
        this.FlightNum = FlightNum;
        this.MaxAirTime = MaxAirTime;
    }
    public Integer getFlightNum() {
        return FlightNum;
    }
    public Integer getMaxAirTime() {
        return MaxAirTime;
    }
}
