package com.mjamsek.metrics.api.v1.endpoints;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.mjamsek.metrics.entities.socket.SocketMessage;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/socket")
public class SocketEndpoint {
    
    private static final Logger LOG = LogManager.getLogger(SocketEndpoint.class.getName());
    
    @OnMessage
    public void onMessage(String stringifiedMessage, Session session) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            SocketMessage socketMessage = objectMapper.readValue(stringifiedMessage, SocketMessage.class);
            LOG.info("Received socket message of type '{}'", socketMessage.getType().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @OnOpen
    public void onOpen(Session session) {
    
    }
    
    @OnClose
    public void onClose(Session session) {
    
    }
    
    @OnError
    public void onError(Throwable throwable, Session session) {
    
    }
}
