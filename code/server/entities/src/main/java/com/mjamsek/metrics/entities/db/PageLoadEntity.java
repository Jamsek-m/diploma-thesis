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
            ") FROM PageLoadEntity p " +
            "WHERE p.application = :application AND p.firstPage = false " +
            "GROUP BY p.pathname"),
    @NamedQuery(name = PageLoadEntity.GET_BY_PAGE_WITH_FIRST,
        query = "SELECT new com.mjamsek.metrics.lib.load.SinglePageReport(" +
            "p.pathname, " +
            "min(p.loadEnd - p.loadStart), " +
            "max(p.loadEnd - p.loadStart), " +
            "avg(p.loadEnd - p.loadStart), " +
            "count(p)" +
            ") FROM PageLoadEntity p " +
            "WHERE p.application = :application " +
            "GROUP BY p.pathname"),
    @NamedQuery(name = PageLoadEntity.AVG_PAGE_LOAD,
        query = "SELECT avg(p.loadEnd - p.loadStart) FROM PageLoadEntity p " +
            "WHERE p.application = :application AND p.firstPage = false"),
    @NamedQuery(name = PageLoadEntity.AVG_PAGE_LOAD_WITH_FIRST,
        query = "SELECT avg(p.loadEnd - p.loadStart) FROM PageLoadEntity p " +
            "WHERE p.application = :application")
})
@NamedNativeQueries({
    // @formatter:off
    @NamedNativeQuery(name = PageLoadEntity.CALC_PERCENTILE,
        query = "WITH dataset AS ( " +
                   "SELECT (p.load_end - p.load_start) AS diff, " +
                   "p.pathname " +
                   "FROM page_loads p " +
                   "WHERE p.application_name = ?1 " +
                   "AND p.first_page = false) " +
            "SELECT percentile_cont(?2) WITHIN GROUP (ORDER BY dataset.diff) AS percentile, " +
            "dataset.pathname " +
            "FROM dataset " +
            "GROUP BY dataset.pathname;"),
    @NamedNativeQuery(name = PageLoadEntity.CALC_PERCENTILE_WITH_FIRST,
        query = "WITH dataset AS ( " +
                   "SELECT (p.load_end - p.load_start) AS diff, " +
                   "p.pathname " +
                   "FROM page_loads p " +
                   "WHERE p.application_name = ?1) " +
            "SELECT percentile_cont(?2) WITHIN GROUP (ORDER BY dataset.diff) AS percentile, " +
            "dataset.pathname " +
            "FROM dataset " +
            "GROUP BY dataset.pathname;")
    // @formatter:on
})
public class PageLoadEntity extends SessionBaseEntity {
    
    public static final String GET_BY_PAGE = "PageLoadEntity.getByPage";
    public static final String GET_BY_PAGE_WITH_FIRST = "PageLoadEntity.getByPageWithFirstPage";
    public static final String AVG_PAGE_LOAD = "PageLoadEntity.avgPageLoad";
    public static final String AVG_PAGE_LOAD_WITH_FIRST = "PageLoadEntity.avgPageLoadWithFirstPage";
    public static final String CALC_PERCENTILE = "PageLoadEntity.calcPercentile";
    public static final String CALC_PERCENTILE_WITH_FIRST = "PageLoadEntity.calcPercentileWithFirstPage";
    
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
