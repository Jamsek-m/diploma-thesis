package com.mjamsek.metrics.entities.db;

import javax.persistence.*;

@Entity
@Table(name = "app_startups")
@NamedQueries(value = {
    @NamedQuery(name = AppStartupEntity.FIND_BY_APP_NAME,
        query = "SELECT a from AppStartupEntity a WHERE a.application = :application"),
    @NamedQuery(name = AppStartupEntity.MAX_TIME_DIFF, query = "SELECT max(a.applicationLoaded - a.navigationStart) as diff FROM AppStartupEntity a WHERE a.application = :application"),
    @NamedQuery(name = AppStartupEntity.MIN_TIME_DIFF, query = "SELECT min(a.applicationLoaded - a.navigationStart) as diff FROM AppStartupEntity a WHERE a.application = :application"),
    @NamedQuery(name = AppStartupEntity.AVG_TIME_DIFF, query = "SELECT avg(a.applicationLoaded - a.navigationStart) as diff FROM AppStartupEntity a WHERE a.application = :application")
})
public class AppStartupEntity extends SessionBaseEntity {
    
    public static final String FIND_BY_APP_NAME = "AppStartup.findByAppName";
    public static final String MIN_TIME_DIFF = "AppStartup.minTimeDiff";
    public static final String MAX_TIME_DIFF = "AppStartup.maxTimeDiff";
    public static final String AVG_TIME_DIFF = "AppStartup.avgTimeDiff";
    
    @Column(name = "navigation_start")
    private Long navigationStart;
    
    @Column(name = "application_loaded")
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
