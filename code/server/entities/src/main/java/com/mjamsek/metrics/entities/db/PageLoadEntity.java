package com.mjamsek.metrics.entities.db;

import javax.persistence.*;

@Entity
@Table(name = "page_loads")
public class PageLoadEntity extends SessionBaseEntity {
    
    @Column(name = "pathname")
    private String pathname;
    
    @Column(name = "load_start")
    private Long loadStart;
    
    @Column(name = "load_end")
    private Long loadEnd;
    
    @Column(name = "first_page")
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
