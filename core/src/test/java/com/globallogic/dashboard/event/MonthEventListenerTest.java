package com.globallogic.dashboard.event;

import org.junit.Test;

public class MonthEventListenerTest {

    @Test
    public void fireEvent() {
        MonthEventListener monthEventListener = new MonthEventListener();

        StartEvent startEvent = new StartEvent(1);
        EndEvent endEvent = new EndEvent(2);

        monthEventListener.fireEvent(startEvent);

    }
}