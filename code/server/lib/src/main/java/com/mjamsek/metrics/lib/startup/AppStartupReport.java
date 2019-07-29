package com.mjamsek.metrics.lib.startup;

import com.mjamsek.metrics.lib.load.SinglePercentileReport;

import java.util.ArrayList;
import java.util.List;

public class AppStartupReport {
    
    private Long minLoadTime;
    private Long maxLoadTime;
    private Double avgLoadTime;
    private List<SinglePercentileReport> percentiles;
    
    public AppStartupReport() {
        this.percentiles = new ArrayList<>();
    }
    
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
    
    public List<SinglePercentileReport> getPercentiles() {
        return percentiles;
    }
    
    public void setPercentiles(List<SinglePercentileReport> percentiles) {
        this.percentiles = percentiles;
    }
}
