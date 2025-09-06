package com.mhdx.springweb.model.Web;

public class OriginGet {
    private String origin;
    private Integer maxDelay;

    public OriginGet(String origin, Integer maxDelay) {
        this.origin = origin;
        this.maxDelay = maxDelay;
    }

    public String getOrigin() {
        return origin;
    }

    public Integer getMaxDelay() {
        return maxDelay;
    }
}
