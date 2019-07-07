package com.mjamsek.metrics.lib.startup;

public class AppStartupReport {
    
    private Long minLoadTime;
    private Long maxLoadTime;
    private Double avgLoadTime;
    
    public Long getMinLoadTime() {
        return minLoadTime;
    }
    
    public void setMinLoadTime(Long minLoadTime) {
        this.minLoadTime = minLoadTime;
    }
    
    public Long getMaxLoadTime() {
        return maxLoadTime;
    }
    
    public void setMaxLoadTime(Long maxLoadTime) {
        this.maxLoadTime = maxLoadTime;
    }
    
    public Double getAvgLoadTime() {
        return avgLoadTime;
    }
    
    public void setAvgLoadTime(Double avgLoadTime) {
        this.avgLoadTime = avgLoadTime;
    }
}
