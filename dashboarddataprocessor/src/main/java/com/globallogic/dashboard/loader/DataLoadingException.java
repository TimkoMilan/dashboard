package com.globallogic.dashboard.loader;

public class DataLoadingException extends RuntimeException {
    public DataLoadingException(String message) {
        super(message);
    }

    public DataLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataLoadingException(Throwable cause) {
        super(cause);
    }

    public DataLoadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
