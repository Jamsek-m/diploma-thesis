package com.mjamsek.metrics.lib.socket.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppStartupMessage extends SocketSessionMessage {
    
    private Long navigationStart;
    
    private Long applicationLoaded;
    
    public Long getNavigationStart() {
        return navigationStart;
    }
    
    public void setNavigationStart(Long navigationStart) {
        this.navigationStart = navigationStart;
    }
    
    public Long getApplicationLoaded() {
        return applicationLoaded;
    }
    
    public void setApplicationLoaded(Long applicationLoaded) {
        this.applicationLoaded = applicationLoaded;
    }
}
