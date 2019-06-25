package com.mjamsek.metrics.services;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Set;


public class SocketSessionContext {
    
    private static Set<Session> sessions;
    
    static {
        sessions = new HashSet<>();
    }
    
    public static void openSession(Session session) {
        sessions.add(session);
    }
    
    public static void closeSession(Session session) {
        sessions.remove(session);
    }
    
    public static Set<Session> getAllSessions() {
        return sessions;
    }
    
}
