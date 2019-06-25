package com.mjamsek.metrics.entities.socket.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mjamsek.metrics.entities.socket.SocketMessage;
import com.mjamsek.metrics.entities.socket.SocketMessageType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "sessionType", visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MouseTrackMessage.class, name = "MOUSE_TRACK")
})
public class SocketSessionMessage extends SocketMessage {
    
    protected SocketSessionType sessionType;
    
    public SocketSessionMessage() {
        this.type = SocketMessageType.SESSION;
    }
    
    public SocketSessionType getSessionType() {
        return sessionType;
    }
    
    public void setSessionType(SocketSessionType sessionType) {
        this.sessionType = sessionType;
    }
}
