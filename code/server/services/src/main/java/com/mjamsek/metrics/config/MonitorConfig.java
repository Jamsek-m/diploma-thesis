package com.mjamsek.metrics.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("metrics-monitor")
public class MonitorConfig {
    
    @ConfigValue("cors-extra.allow-time-origins")
    private String allowedTimeOrigins;
    
    public String getAllowedTimeOrigins() {
        return allowedTimeOrigins;
    }
    
    public void setAllowedTimeOrigins(String allowedTimeOrigins) {
        this.allowedTimeOrigins = allowedTimeOrigins;
    }
}
