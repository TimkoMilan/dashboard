package com.globallogic.dashboard.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingListener implements EventListener<Void> {
    private static final Logger log = LoggerFactory.getLogger(LoggingListener.class);

    @Override
    public void fireEvent(Event event) {
        log.info("Event type logged: {}",event.getClass() );

    }
}
