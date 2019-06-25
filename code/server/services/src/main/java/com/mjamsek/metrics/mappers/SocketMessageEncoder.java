package com.mjamsek.metrics.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjamsek.metrics.entities.socket.SocketMessage;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class SocketMessageEncoder implements Encoder.Text<SocketMessage> {
    
    @Override
    public void init(EndpointConfig config) {
    
    }
    
    @Override
    public String encode(SocketMessage object) throws EncodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String res = objectMapper.writeValueAsString(object);
            System.err.println(res);
            return res;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void destroy() {
    
    }
}
