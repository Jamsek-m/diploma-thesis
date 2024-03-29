package com.mjamsek.metrics.lib.socket.registration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mjamsek.metrics.lib.socket.SocketMessage;
import com.mjamsek.metrics.lib.socket.SocketMessageType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SocketRegistrationRequestMessage extends SocketMessage {

    private String applicationName;
    
    public SocketRegistrationRequestMessage() {
        this.type = SocketMessageType.REGISTRATION_REQUEST;
    }
    
    public String getApplicationName() {
        return applicationName;
    }
    
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
}
