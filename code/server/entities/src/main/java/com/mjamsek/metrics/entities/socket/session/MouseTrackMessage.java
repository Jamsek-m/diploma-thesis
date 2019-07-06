package com.mjamsek.metrics.entities.socket.session;

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
        
        private int pageX;
        private int pageY;
    
        public int getPageX() {
            return pageX;
        }
    
        public void setPageX(int pageX) {
            this.pageX = pageX;
        }
    
        public int getPageY() {
            return pageY;
        }
    
        public void setPageY(int pageY) {
            this.pageY = pageY;
        }
    }
}
