package com.mjamsek.metrics.consumers;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import com.mjamsek.metrics.entities.socket.session.MouseTrackMessage;
import com.mjamsek.metrics.entities.socket.session.SocketSessionMessage;
import com.mjamsek.metrics.entities.socket.session.SocketSessionType;
import com.mjamsek.metrics.mappers.SocketMessageMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import javax.enterprise.context.ApplicationScoped;
import java.util.LinkedList;
import java.util.Queue;

@ApplicationScoped
public class KafkaQueueConsumer {
    
    public static final String KAFKA_TOPIC = "socket-topic";
    
    private static final Logger LOG = LogManager.getLogger(KafkaQueueConsumer.class.getName());
    
    private static Queue<SocketSessionMessage> messages = new LinkedList<>();
    
    @StreamListener(topics = {KAFKA_TOPIC})
    public void onMessage(ConsumerRecord<String, String> record) {
        SocketSessionMessage message = SocketMessageMapper.castSessionMessage(record.value());
        
        if (message != null) {
            LOG.info("Consumed message {}", message.getType());
            
            if (message.getSessionType().equals(SocketSessionType.MOUSE_TRACK)) {
                MouseTrackMessage trackMessage = (MouseTrackMessage) message;
                for (MouseTrackMessage.Coordinates c : trackMessage.getCoordinates()) {
                    System.err.println(c.getPageX() + "," + c.getPageY());
                }
            }
            // KafkaQueueConsumer.messages.add(message);
        }
    }
}
