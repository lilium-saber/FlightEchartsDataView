package com.mhdx.spbhello.EFtable.FlightEF;

public class MonthRateEF {
    private Integer Month;
    private Integer total_count;
    private Integer delay_count;
    private Double delay_ratio;

    public Integer getMonth() {
        return Month;
    }

    public void setMonth(Integer month) {
        Month = month;
    }

    public Integer getTotal_count() {
        return total_count;
    }

    public void setTotal_count(Integer total_count) {
        this.total_count = total_count;
    }

    public Integer getDelay_count() {
        return delay_count;
    }

    public void setDelay_count(Integer delay_count) {
        this.delay_count = delay_count;
    }

    public Double getDelay_ratio() {
        return delay_ratio;
    }

    public void setDelay_ratio(Double delay_ratio) {
        this.delay_ratio = delay_ratio;
    }
}
