package com.mjamsek.metrics.lib.load;

public class SinglePageReport {
    
    private String pathname;
    
    private Long minLoadTime;
    
    private Long maxLoadTime;
    
    private Double avgLoadTime;
    
    private Long pageHits;
    
    public SinglePageReport() {
    }
    
    public SinglePageReport(String pathname, Long minLoadTime, Long maxLoadTime, Double avgLoadTime, Long pageHits) {
        this.pathname = pathname;
        this.minLoadTime = minLoadTime;
        this.maxLoadTime = maxLoadTime;
        this.avgLoadTime = avgLoadTime;
        this.pageHits = pageHits;
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
}
