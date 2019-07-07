package com.mjamsek.metrics.lib.socket.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mjamsek.metrics.lib.socket.SocketMessage;
import com.mjamsek.metrics.lib.socket.SocketMessageType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "sessionType", visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = MouseTrackMessage.class, name = "MOUSE_TRACK"),
    @JsonSubTypes.Type(value = SessionPingMessage.class, name = "PING"),
    @JsonSubTypes.Type(value = AppStartupMessage.class, name = "APP_STARTUP"),
    @JsonSubTypes.Type(value = PageLoadMessage.class, name = "PAGE_LOAD")
})
public class SocketSessionMessage extends SocketMessage {
    
    protected SocketSessionType sessionType;
    
    protected String sessionId;
    
    protected String application;
    
    public SocketSessionMessage() {
        this.type = SocketMessageType.SESSION;
    }
    
    public SocketSessionType getSessionType() {
        return sessionType;
    }
    
    public void setSessionType(SocketSessionType sessionType) {
        this.sessionType = sessionType;
    }
    
    public String getSessionId() {
        return sessionId;
    }
    
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    
    public String getApplication() {
        return application;
    }
    
    public void setApplication(String application) {
        this.application = application;
    }
}
