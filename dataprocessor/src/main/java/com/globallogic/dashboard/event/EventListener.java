package com.globallogic.dashboard.event;

public interface EventListener<T> {
    void fireEvent(Event<T> event);
}
