package com.mjamsek.metrics.lib.exceptions;

public class ApplicationNotFoundException extends MetricsApiException {
    
    public ApplicationNotFoundException() {
        super("Application not found!");
    }
    
    public ApplicationNotFoundException(String message) {
        super(message);
    }
    
    public ApplicationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ApplicationNotFoundException(Throwable cause) {
        super("Application not found!", cause);
    }
    
    protected ApplicationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
