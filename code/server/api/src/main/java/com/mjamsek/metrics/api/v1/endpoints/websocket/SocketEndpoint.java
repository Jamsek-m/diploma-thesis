package com.mjamsek.metrics.api.v1.endpoints.websocket;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.mjamsek.metrics.lib.socket.SocketMessage;
import com.mjamsek.metrics.mappers.SocketMessageDecoder;
import com.mjamsek.metrics.mappers.SocketMessageEncoder;
import com.mjamsek.metrics.services.SocketService;
import com.mjamsek.metrics.services.SocketSessionContext;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@ServerEndpoint(value = "/socket", decoders = SocketMessageDecoder.class, encoders = SocketMessageEncoder.class)
public class SocketEndpoint {
    
    private static final Logger LOG = LogManager.getLogger(SocketEndpoint.class.getName());
    
    @Inject
    private SocketService socketService;
    
    @OnMessage
    public void onMessage(SocketMessage message, Session session) {
        if (message != null) {
            LOG.info("Received socket message of type '{}'", message.getType().getName());
            socketService.processSocketMessage(message, session);
        }
    }
    
    @OnOpen
    public void onOpen(Session session) {
        LOG.debug("New socket connection with id {}", session.getId());
    }
    
    @OnClose
    public void onClose(Session session) {
        LOG.debug("Closing session with id {}", session.getId());
        SocketSessionContext.closeSession(session);
    }
    
    @OnError
    public void onError(Throwable throwable, Session session) {
        LOG.error("Session id: {}, error: {}", session.getId(), throwable.getMessage());
        throwable.printStackTrace();
    
        if (throwable.getCause() instanceof TimeoutException) {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.SERVICE_RESTART, "Timeout"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
