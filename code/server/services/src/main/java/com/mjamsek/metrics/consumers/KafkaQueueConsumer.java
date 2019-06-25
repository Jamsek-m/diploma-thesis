package com.mjamsek.metrics.consumers;

import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import com.mjamsek.metrics.entities.socket.session.SocketSessionMessage;
import com.mjamsek.metrics.mappers.JacksonMapper;
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
        SocketSessionMessage message = JacksonMapper.toEntity(record.value(), SocketSessionMessage.class);
        if (message != null) {
            LOG.info("Consumed message {}", message.getType());
            // KafkaQueueConsumer.messages.add(message);
        }
    }
}
