package com.mjamsek.metrics.utils;

public class UrlUtil {
    
    public static String normalizePathname(String pathname) {
        if (pathname.equals("/") || pathname.equals("#")) {
            return pathname;
        }
        
        if (pathname.endsWith("/")) {
            pathname = pathname.substring(0, pathname.length() - 1);
        }
        
        if (!pathname.startsWith("/")) {
            pathname = "/" + pathname;
        }
        
        return pathname;
    }
    
}
