package com.mjamsek.metrics.lib.socket.session;

public enum SocketSessionType {
    PING("PING"),
    MOUSE_TRACK("MOUSE_TRACK"),
    APP_STARTUP("APP_STARTUP"),
    PAGE_LOAD("PAGE_LOAD"),
    RESOURCE_LOAD("RESOURCE_LOAD");
    
    private String name;
    
    SocketSessionType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
