package com.mjamsek.metrics.consumers;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import com.mjamsek.metrics.lib.socket.session.AppStartupMessage;
import com.mjamsek.metrics.lib.socket.session.MouseTrackMessage;
import com.mjamsek.metrics.lib.socket.session.SocketSessionMessage;
import com.mjamsek.metrics.lib.socket.session.SocketSessionType;
import com.mjamsek.metrics.mappers.SocketMessageMapper;
import com.mjamsek.metrics.services.MetricsService;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class KafkaQueueConsumer {
    
    public static final String KAFKA_TOPIC = "socket-topic";
    
    private static final Logger LOG = LogManager.getLogger(KafkaQueueConsumer.class.getName());
    
    @Inject
    private MetricsService metricsService;
    
    @StreamListener(topics = {KAFKA_TOPIC})
    public void onMessage(ConsumerRecord<String, String> record) {
        SocketSessionMessage message = SocketMessageMapper.castSessionMessage(record.value());
        
        if (message != null) {
            LOG.info("Consumed message {}", message.getType());
            
            if (message.getSessionType().equals(SocketSessionType.MOUSE_TRACK)) {
                MouseTrackMessage trackMessage = (MouseTrackMessage) message;
                
                metricsService.handleMouseTracking(trackMessage);
                LOG.info("Coordinates for session '{}' persisted", trackMessage.getSessionId());
            } else if (message.getSessionType().equals(SocketSessionType.APP_STARTUP)) {
                AppStartupMessage appStartupMessage = (AppStartupMessage) message;
                metricsService.handleAppStartupTracking(appStartupMessage);
            }
        }
    }
}
