package com.globallogic.dashboard.event;

public class FinishEvent<T>  extends Event<T> {
    private int end;

    public FinishEvent(int end) {
        this.end = end;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return super.toString() + "FinishEvent{" +
                "end=" + end +
                '}';
    }
}
