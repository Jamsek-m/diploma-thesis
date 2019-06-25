package com.mjamsek.metrics.mappers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kumuluz.ee.logs.LogManager;
import com.kumuluz.ee.logs.Logger;
import com.mjamsek.metrics.entities.socket.SocketMessage;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

public class SocketMessageDecoder implements Decoder.Text<SocketMessage> {
    
    private static final Logger LOG = LogManager.getLogger(SocketMessageDecoder.class.getName());
    
    @Override
    public void init(EndpointConfig config) {
    
    }
    
    @Override
    public SocketMessage decode(String s) throws DecodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(s, SocketMessage.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public boolean willDecode(String s) {
        return true;
    }
    
    @Override
    public void destroy() {
    
    }
}
