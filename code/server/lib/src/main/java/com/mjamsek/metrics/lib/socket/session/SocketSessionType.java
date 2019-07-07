package com.mjamsek.metrics.lib.socket.session;

public enum SocketSessionType {
    PING("PING"),
    MOUSE_TRACK("MOUSE_TRACK"),
    APP_STARTUP("APP_STARTUP");
    
    private String name;
    
    SocketSessionType(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
}
