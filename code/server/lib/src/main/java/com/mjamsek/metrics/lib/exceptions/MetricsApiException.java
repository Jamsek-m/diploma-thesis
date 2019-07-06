package com.mjamsek.metrics.lib.exceptions;

public class MetricsApiException extends RuntimeException {
    
    public MetricsApiException() {
        super();
    }
    
    public MetricsApiException(String message) {
        super(message);
    }
    
    public MetricsApiException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public MetricsApiException(Throwable cause) {
        super(cause);
    }
    
    protected MetricsApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
