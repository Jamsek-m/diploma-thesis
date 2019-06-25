package com.mjamsek.metrics.services;

import com.mjamsek.metrics.entities.socket.SocketMessage;

import javax.websocket.Session;

public interface SocketService {
    
    void registerSocketSession(String applicationName, Session session);
    
    void processSocketMessage(SocketMessage message, Session session);
    
    void sendMessage(SocketMessage message, Session session);
}
