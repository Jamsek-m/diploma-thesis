package com.mjamsek.metrics.lib.socket.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PageLoadMessage extends SocketSessionMessage {
    
    private String pathname;
    
    private Long loadStart;
    
    private Long loadEnd;
    
    private Boolean firstPage;
    
    public String getPathname() {
        return pathname;
    }
    
    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
    
    public Long getLoadStart() {
        return loadStart;
    }
    
    public void setLoadStart(Long loadStart) {
        this.loadStart = loadStart;
    }
    
    public Long getLoadEnd() {
        return loadEnd;
    }
    
    public void setLoadEnd(Long loadEnd) {
        this.loadEnd = loadEnd;
    }
    
    public Boolean getFirstPage() {
        return firstPage;
    }
    
    public void setFirstPage(Boolean firstPage) {
        this.firstPage = firstPage;
    }
}
