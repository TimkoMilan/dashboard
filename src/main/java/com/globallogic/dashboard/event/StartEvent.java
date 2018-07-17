package com.globallogic.dashboard.event;

public class StartEvent extends Event {
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
