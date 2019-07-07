package com.mjamsek.metrics.entities.db;

import javax.persistence.*;

@Entity
@Table(name = "mouse_track_records")
@NamedQueries({
    @NamedQuery(name = MouseTrackRecordEntity.FIND_BY_SESSION_ID,
        query = "SELECT m FROM MouseTrackRecordEntity m WHERE m.sessionId = :sessionId ORDER BY m.timestamp"),
    @NamedQuery(name = MouseTrackRecordEntity.FIND_BY_APP_NAME,
        query = "SELECT m FROM MouseTrackRecordEntity m " +
            "WHERE m.application = :application AND m.heatLevel >= :heatLevel AND m.pathname = :pathname"),
    @NamedQuery(name = MouseTrackRecordEntity.FIND_EXISTING_COORDINATES,
        query = "SELECT m FROM MouseTrackRecordEntity m WHERE m.application = :application " +
            "AND m.sessionId = :sessionId AND m.mouseX = :mouseX AND m.mouseY = :mouseY AND m.pathname = :pathname")
})
public class MouseTrackRecordEntity extends SessionBaseEntity {
    
    public static final String FIND_BY_SESSION_ID = "MouseTrackRecord.findBySessionId";
    public static final String FIND_BY_APP_NAME = "MouseTrackRecord.findByAppName";
    public static final String FIND_EXISTING_COORDINATES = "MouseTrackRecord.findExistingCoordinates";
    
    @Column(name = "mouse_x")
    private Integer mouseX;
    
    @Column(name = "mouse_y")
    private Integer mouseY;
    
    @Column(name = "heat_level")
    private Long heatLevel;
    
    @Column(name = "pathname")
    private String pathname;
    
    public Integer getMouseX() {
        return mouseX;
    }
    
    public void setMouseX(Integer mouseX) {
        this.mouseX = mouseX;
    }
    
    public Integer getMouseY() {
        return mouseY;
    }
    
    public void setMouseY(Integer mouseY) {
        this.mouseY = mouseY;
    }
    
    public Long getHeatLevel() {
        return heatLevel;
    }
    
    public void setHeatLevel(Long heatLevel) {
        this.heatLevel = heatLevel;
    }
    
    public void incrementHeat(Long addLevels) {
        this.heatLevel += addLevels;
    }
    
    public String getPathname() {
        return pathname;
    }
    
    public void setPathname(String pathname) {
        this.pathname = pathname;
    }
}
