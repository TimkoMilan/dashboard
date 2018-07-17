package com.globallogic.dashboard.event;

public class Event {
    private String payload;
    private Object context;


    public Event(String payload) {
        this.payload = payload;
    }

    public Event() {
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
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
