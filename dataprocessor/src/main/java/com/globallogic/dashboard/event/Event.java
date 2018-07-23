package com.globallogic.dashboard.event;

public class Event <T>{
    private T payload;
    private Object context;


    public Event(T payload) {
        this.payload = payload;
    }

    public Event() {
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public void setContext(Object context) {
        this.context = context;
    }

    public Object getContext() {
        return context;
    }

    @Override
    public String toString() {
        return "Event{" +
                "payload='" + payload + '\'' +
                '}';
    }
}
