package com.mjamsek.metrics.mappers;

import com.mjamsek.metrics.lib.socket.SocketMessage;
import com.mjamsek.metrics.lib.socket.registration.SocketRegistrationRequestMessage;
import com.mjamsek.metrics.lib.socket.registration.SocketRegistrationResponseMessage;
import com.mjamsek.metrics.lib.socket.session.AppStartupMessage;
import com.mjamsek.metrics.lib.socket.session.MouseTrackMessage;
import com.mjamsek.metrics.lib.socket.session.SessionPingMessage;
import com.mjamsek.metrics.lib.socket.session.SocketSessionMessage;

public class SocketMessageMapper {
    
    public static SocketMessage castMessage(SocketMessage rootMessage, String rawMessage) {
        
        if (rootMessage.getType() == null) {
            return null;
        }
        
        switch (rootMessage.getType()) {
            case REGISTRATION_REQUEST:
                return JacksonMapper.toEntity(rawMessage, SocketRegistrationRequestMessage.class);
            case REGISTRATION_RESPONSE:
                return JacksonMapper.toEntity(rawMessage, SocketRegistrationResponseMessage.class);
            case SESSION:
                return castSessionMessage(rawMessage);
            default:
                return null;
        }
    }
    
    public static SocketSessionMessage castSessionMessage(String rawMessage) {
        
        SocketSessionMessage sessionMessage = JacksonMapper.toEntity(rawMessage, SocketSessionMessage.class);
        
        if (sessionMessage == null || sessionMessage.getSessionType() == null) {
            return null;
        }
        
        switch (sessionMessage.getSessionType()) {
            case MOUSE_TRACK:
                return JacksonMapper.toEntity(rawMessage, MouseTrackMessage.class);
            case PING:
                return JacksonMapper.toEntity(rawMessage, SessionPingMessage.class);
            case APP_STARTUP:
                return JacksonMapper.toEntity(rawMessage, AppStartupMessage.class);
            default:
                return null;
        }
    }
}
