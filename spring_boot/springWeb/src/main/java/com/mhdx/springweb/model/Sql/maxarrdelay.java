package com.mhdx.springweb.model.Sql;

public class maxarrdelay {
    private String Dest;
    private Integer MaxArrDelay;

    public maxarrdelay(String Dest, Integer MaxArrDelay) {
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
