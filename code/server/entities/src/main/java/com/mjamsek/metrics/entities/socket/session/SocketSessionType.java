package com.mjamsek.metrics.entities.socket.session;

public enum SocketSessionType {
    MOUSE_TRACK("MOUSE_TRACK");
    
    private String name;
    
    SocketSessionType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
