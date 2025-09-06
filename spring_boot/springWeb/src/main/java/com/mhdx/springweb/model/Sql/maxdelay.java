package com.mhdx.springweb.model.Sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

public class maxdelay {
    private String DelayType;
    private Integer MaxDelay;

    public maxdelay(String DelayType, Integer MaxDelay) {
        this.DelayType = DelayType;
        this.MaxDelay = MaxDelay;
    }
    public String getDelayType() {
        return DelayType;
    }
    public Integer getMaxDelay() {
        return MaxDelay;
    }
}
