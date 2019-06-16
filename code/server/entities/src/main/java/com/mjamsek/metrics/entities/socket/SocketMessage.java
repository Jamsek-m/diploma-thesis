package com.mjamsek.metrics.entities.socket;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", visible = true)
@JsonSubTypes(
    @JsonSubTypes.Type(value = SocketRegistrationMessage.class, name = "REGISTRATION")
)
public class SocketMessage {
    
    private SocketMessageType type;
    
    public SocketMessageType getType() {
        return type;
    }
    
    public void setType(SocketMessageType type) {
        this.type = type;
    }
}
