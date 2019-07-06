package com.mjamsek.metrics.entities.db;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SessionBaseEntity extends BaseEntity {
    
    @NotNull
    @Column(name = "session_id")
    protected String sessionId;
    
    @NotNull
    @Column(name = "application_name")
    protected String application;
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public String getApplication() {
        return application;
    }
    
    public void setApplication(String application) {
        this.application = application;
    }
}
