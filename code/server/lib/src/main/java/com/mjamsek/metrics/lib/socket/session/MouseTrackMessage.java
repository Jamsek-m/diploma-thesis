package com.mjamsek.metrics.lib.socket.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MouseTrackMessage extends SocketSessionMessage {
    
    private List<Coordinates> coordinates;
    
    public MouseTrackMessage() {
        super();
    }
    
    public List<Coordinates> getCoordinates() {
        return coordinates;
    }
    
    public void setCoordinates(List<Coordinates> coordinates) {
        this.coordinates = coordinates;
    }
    
    public static class Coordinates {
        
        private Integer pageX;
        private Integer pageY;
        
        public Integer getPageX() {
            return pageX;
        }
        
        public void setPageX(Integer pageX) {
            this.pageX = pageX;
        }
        
        public Integer getPageY() {
            return pageY;
        }
        
        public void setPageY(Integer pageY) {
            this.pageY = pageY;
        }
    }
}
