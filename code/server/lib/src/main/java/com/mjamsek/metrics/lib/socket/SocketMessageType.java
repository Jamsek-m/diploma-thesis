package com.mjamsek.metrics.lib.socket;

public enum SocketMessageType {
    SESSION("SESSION"),
    REGISTRATION_RESPONSE("REGISTRATION_RESPONSE"),
    REGISTRATION_REQUEST("REGISTRATION_REQUEST");
    
    private String name;
    
    SocketMessageType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
