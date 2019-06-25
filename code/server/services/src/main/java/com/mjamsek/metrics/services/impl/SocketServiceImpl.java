package com.mjamsek.metrics.services.impl;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.streaming.common.annotations.StreamProducer;
import com.mjamsek.metrics.consumers.KafkaQueueConsumer;
import com.mjamsek.metrics.entities.socket.SocketMessage;
import com.mjamsek.metrics.entities.socket.registration.SocketRegistrationRequestMessage;
import com.mjamsek.metrics.entities.socket.registration.SocketRegistrationResponseMessage;
import com.mjamsek.metrics.entities.socket.session.SocketSessionMessage;
import com.mjamsek.metrics.mappers.JacksonMapper;
import com.mjamsek.metrics.services.SocketService;
import com.mjamsek.metrics.services.SocketSessionContext;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.Session;
import java.util.Set;
import java.util.UUID;

@ApplicationScoped
public class SocketServiceImpl implements SocketService {
    
    private static final Logger LOG = LogManager.getLogger(SocketServiceImpl.class.getName());
    
    @Inject
    @StreamProducer
    private Producer<String, String> producer;
    
    @Override
    public void registerSocketSession(String applicationName, Session session) {
        session.getUserProperties().put("appName", applicationName);
        session.getUserProperties().put("sessionId", UUID.randomUUID().toString());
        SocketSessionContext.openSession(session);
        // TODO: save connection to DB
    }
    
    @Override
    public void processSocketMessage(SocketMessage message, Session session) {
        switch (message.getType()) {
            case REGISTRATION_REQUEST:
                this.handleRegistrationMessage(message, session);
                break;
            case SESSION:
                this.handleSessionMessage((SocketSessionMessage) message, session);
                break;
        }
    }
    
    private void handleRegistrationMessage(SocketMessage message, Session session) {
        SocketRegistrationRequestMessage request = (SocketRegistrationRequestMessage) message;
    
        this.registerSocketSession(request.getApplicationName(), session);
    
        SocketRegistrationResponseMessage response = new SocketRegistrationResponseMessage();
        response.setSessionId((String) session.getUserProperties().get("sessionId"));
    
        LOG.info("Registered session with id {} and tracking id {}", session.getId(), session.getUserProperties().get("sessionId"));
        this.sendMessage(response, session);
    }
    
    private void handleSessionMessage(SocketSessionMessage message, Session session) {
        String stringifiedMessage = JacksonMapper.stringify(message);
    
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
            KafkaQueueConsumer.KAFKA_TOPIC,
            "key",
            stringifiedMessage
        );
        
        producer.send(record, (metadata, exception) -> {
            if (exception != null) {
                exception.printStackTrace();
            } else {
                LOG.info("Message for session '{}' was sent to kafka server!", "todo");
            }
        });
    }
    
    @Override
    public void sendMessage(SocketMessage message, Session receivingSession) {
        if (receivingSession != null) {
            receivingSession.getAsyncRemote().sendObject(message);
        } else {
            Set<Session> sessions = SocketSessionContext.getAllSessions();
            sessions.forEach(session -> {
                session.getAsyncRemote().sendObject(message);
            });
        }
    }
}
