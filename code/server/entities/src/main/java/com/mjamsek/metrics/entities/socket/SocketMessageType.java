package com.mjamsek.metrics.entities.socket;

public enum SocketMessageType {
    REGISTRATION("REGISTRATION");
    
    private String name;
    
    SocketMessageType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
