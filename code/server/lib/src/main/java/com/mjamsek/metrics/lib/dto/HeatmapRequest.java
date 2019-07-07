package com.mjamsek.metrics.lib.dto;

public class HeatmapRequest {
    
    private String applicationName;
    
    private String pathname;
    
    public String getApplicationName() {
        return applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    
    public String getPathname() {
        return pathname;
    }
    
    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
}
