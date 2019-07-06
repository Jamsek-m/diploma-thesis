package com.mjamsek.metrics.lib.socket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.mjamsek.metrics.lib.socket.registration.SocketRegistrationRequestMessage;
import com.mjamsek.metrics.lib.socket.registration.SocketRegistrationResponseMessage;
import com.mjamsek.metrics.lib.socket.session.SocketSessionMessage;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type", visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = SocketRegistrationRequestMessage.class, name = "REGISTRATION_REQUEST"),
    @JsonSubTypes.Type(value = SocketRegistrationResponseMessage.class, name = "REGISTRATION_RESPONSE"),
    @JsonSubTypes.Type(value = SocketSessionMessage.class, name = "SESSION")
})
public class SocketMessage {
    
    protected SocketMessageType type;
    
    public SocketMessageType getType() {
        return type;
    }
    
    public void setType(SocketMessageType type) {
        this.type = type;
    }
}
