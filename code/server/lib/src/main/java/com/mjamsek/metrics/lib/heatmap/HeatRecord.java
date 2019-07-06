package com.mjamsek.metrics.lib.heatmap;

public class HeatRecord {
    
    private Integer mouseX;
    
    private Integer mouseY;
    
    private Long heatLevel;
    
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
}
