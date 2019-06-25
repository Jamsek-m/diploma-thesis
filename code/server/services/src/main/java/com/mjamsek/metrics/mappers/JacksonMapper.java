package com.mjamsek.metrics.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonMapper {
    
    public static String stringify(Object entity) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static <T> T toEntity(String stringifiedEntity, Class<T> entityType) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(stringifiedEntity, entityType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
