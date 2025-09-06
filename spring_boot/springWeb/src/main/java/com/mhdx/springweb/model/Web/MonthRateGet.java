package com.mhdx.springweb.model.Web;

public class MonthRateGet {
    private Integer month;
    private String delay_ratio;

    public MonthRateGet(Integer month, String delay_ratio) {
        this.month = month;
        this.delay_ratio = delay_ratio;
    }
    public Integer getMonth() {
        return month;
    }
    public String getDelay_ratio() {
        return delay_ratio;
    }
}
