package com.mjamsek.metrics.entities.db;

import javax.persistence.*;

@Entity
@Table(name = "page_loads")
@NamedQueries({
    @NamedQuery(name = PageLoadEntity.GET_BY_PAGE,
        query = "SELECT new com.mjamsek.metrics.lib.load.SinglePageReport(" +
            "p.pathname, " +
            "min(p.loadEnd - p.loadStart), " +
            "max(p.loadEnd - p.loadStart), " +
            "avg(p.loadEnd - p.loadStart), " +
            "count(p)" +
        ") FROM PageLoadEntity p WHERE p.application = :application GROUP BY p.pathname"),
    @NamedQuery(name = PageLoadEntity.AVG_PAGE_LOAD,
        query = "SELECT avg(p.loadEnd - p.loadStart) FROM PageLoadEntity p WHERE p.application = :application")
})
public class PageLoadEntity extends SessionBaseEntity {
    
    public static final String GET_BY_PAGE = "PageLoadEntity.getByPage";
    public static final String AVG_PAGE_LOAD = "PageLoadEntity.avgPageLoad";
    
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
