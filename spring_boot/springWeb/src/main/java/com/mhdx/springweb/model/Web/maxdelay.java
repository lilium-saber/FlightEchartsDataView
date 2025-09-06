package com.mhdx.springweb.model.Web;

public class maxdelay {
    private String Dest;
    private Integer MaxArrDelay;

    public maxdelay(String Dest, Integer MaxArrDelay) {
        this.Dest = Dest;
        this.MaxArrDelay = MaxArrDelay;
    }
    public String getDest() {
        return Dest;
    }
    public Integer getMaxArrDelay() {
        return MaxArrDelay;
    }
}
