package com.globallogic.dashboard.common;

import java.text.MessageFormat;

public class BusinessException extends RuntimeException {
    public BusinessException(String message, String... params) {
        super(MessageFormat.format(message, params));
    }
}
