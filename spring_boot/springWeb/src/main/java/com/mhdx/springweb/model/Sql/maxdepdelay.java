package com.mhdx.springweb.model.Sql;


public class maxdepdelay {
    private String Origin;
    private Integer MaxDepDelay;

    public maxdepdelay(String Origin, Integer MaxDepDelay) {
        this.Origin = Origin;
        this.MaxDepDelay = MaxDepDelay;
    }
    public Integer getMaxDepDelay() {
        return MaxDepDelay;
    }
    public String getOrigin() {
        return Origin;
    }
}
