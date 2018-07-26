package com.globallogic.dashboard.event;

public class DataProcessorException extends RuntimeException {
    public DataProcessorException(String message) {
        super(message);
    }

    public DataProcessorException(String message, Throwable cause) {
        super(message, cause);
    }
}
