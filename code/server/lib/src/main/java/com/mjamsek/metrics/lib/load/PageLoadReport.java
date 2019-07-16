package com.mjamsek.metrics.lib.load;

import java.util.List;

public class PageLoadReport {
    
    private List<SinglePageReport> pages;
    
    private Double averagePageLoadTime;
    
    public List<SinglePageReport> getPages() {
        return pages;
    }
    
    public void setPages(List<SinglePageReport> pages) {
        this.pages = pages;
    }
    
    public Double getAveragePageLoadTime() {
        return averagePageLoadTime;
    }
    
    public void setAveragePageLoadTime(Double averagePageLoadTime) {
        this.averagePageLoadTime = averagePageLoadTime;
    }
}
