package com.mjamsek.metrics.entities.querymodels;

public class PageLoadPercentileQueryModel {
    
    private Double percentile;
    
    private String pathname;
    
    public Double getPercentile() {
        return percentile;
    }
    
    public void setPercentile(Double percentile) {
        this.percentile = percentile;
    }
    
    public String getPathname() {
        return pathname;
    }
    
    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
}
