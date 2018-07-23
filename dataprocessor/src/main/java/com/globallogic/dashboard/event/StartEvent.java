package com.globallogic.dashboard.event;

public class StartEvent<T> extends Event<T> {
    private int start;

    public StartEvent(int start) {
        this.start = start;
    }

    public int getStart() {
        return start;
    }

    @Override
    public String toString() {
        return super.toString() + "StartEvent{" +
                "start=" + start +
                '}';
    }
}
