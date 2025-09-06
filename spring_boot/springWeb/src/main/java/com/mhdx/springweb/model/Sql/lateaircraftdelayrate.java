package com.mhdx.springweb.model.Sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class lateaircraftdelayrate {
    private String UniqueCarrier;
    private Integer FlightNum;
    private Double DelayPercentage;
}
