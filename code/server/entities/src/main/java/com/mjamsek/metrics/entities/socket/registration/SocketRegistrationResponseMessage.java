package com.mjamsek.metrics.entities.socket.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mjamsek.metrics.entities.socket.SocketMessage;
import com.mjamsek.metrics.entities.socket.SocketMessageType;

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
