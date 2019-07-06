package com.mjamsek.metrics.lib.socket.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mjamsek.metrics.lib.socket.SocketMessage;
import com.mjamsek.metrics.lib.socket.SocketMessageType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SocketRegistrationResponseMessage extends SocketMessage {
    
    private String sessionId;
    
    public SocketRegistrationResponseMessage() {
        this.type = SocketMessageType.REGISTRATION_RESPONSE;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
