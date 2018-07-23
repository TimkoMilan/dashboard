package com.globallogic.dashboard.event;

public class EndEvent<T> extends Event<T> {
   private int end;

    public EndEvent(int end) {
        this.end = end;
    }

    public int getEnd() {
        return end;
    }


    @Override
    public String toString() {
        return super.toString()+"EndEvent{" +
                "end=" + end +
                '}';
    }
}
