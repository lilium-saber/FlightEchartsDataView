package com.mhdx.springweb.model.Sql;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

public class monthRate {
    @Getter
    private Integer month;
    @Getter
    private Integer total_count;
    @Getter
    private Integer delayed_count;
    @Getter
    private Double delay_rate;

    public monthRate(Integer month, Integer total_count, Integer delayed_count, Double delay_rate) {
        this.month = month;
        this.total_count = total_count;
        this.delayed_count = delayed_count;
        this.delay_rate = delay_rate;
    }
}
