package com.mjamsek.metrics.lib.load;

import java.util.ArrayList;
import java.util.List;

public class SinglePageReport {
    
    private String pathname;
    
    private Long minLoadTime;
    
    private Long maxLoadTime;
    
    private Double avgLoadTime;
    
    private Long pageHits;
    
    private List<PagePercentileReport> percentiles;
    
    public SinglePageReport() {
        this.percentiles = new ArrayList<>();
    }
    
    public SinglePageReport(String pathname, Long minLoadTime, Long maxLoadTime, Double avgLoadTime, Long pageHits) {
        this.pathname = pathname;
        this.minLoadTime = minLoadTime;
        this.maxLoadTime = maxLoadTime;
        this.avgLoadTime = avgLoadTime;
        this.pageHits = pageHits;
        this.percentiles = new ArrayList<>();
    }
    
    public String getPathname() {
        return pathname;
    }
    
    public void setPathname(String pathname) {
        this.pathname = pathname;
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
    
    public Long getPageHits() {
        return pageHits;
    }
    
    public void setPageHits(Long pageHits) {
        this.pageHits = pageHits;
    }
    
    public List<PagePercentileReport> getPercentiles() {
        return percentiles;
    }
    
    public void setPercentiles(List<PagePercentileReport> percentiles) {
        this.percentiles = percentiles;
    }
}
