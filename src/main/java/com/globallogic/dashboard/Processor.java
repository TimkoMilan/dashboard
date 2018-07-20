package com.globallogic.dashboard;

import com.globallogic.dashboard.evaluator.EndDataEvaluator;
import com.globallogic.dashboard.evaluator.EvaluatorContext;
import com.globallogic.dashboard.evaluator.StartDataEvaluator;
import com.globallogic.dashboard.event.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Processor<T> {

    private StartDataEvaluator<T> startDataEvaluator;
    private EndDataEvaluator<T> endDataEvaluator;
    private Set<EventListener> eventListeners = new HashSet<>();

    public Processor(StartDataEvaluator<T> startDataEvaluator, EndDataEvaluator<T> endDataEvaluator) {
        this.startDataEvaluator = startDataEvaluator;
        this.endDataEvaluator = endDataEvaluator;
    }

    public Processor withEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
        return this;
    }

    public void process(List<T> rowData) {
        EvaluatorContext<T> evaluatorContext = new EvaluatorContext<>(rowData);
        boolean started = false;
        for (int i = 0; i < rowData.size(); i++) {
            T currentData = rowData.get(i);
            T previousData = resolvePreviousData(rowData, i);
            if (started && endDataEvaluator.isEndData(currentData, previousData, evaluatorContext)) {
                started = false;
                fireEndEvent(i - 1, currentData, rowData);
            }
            if (!started && startDataEvaluator.isStartData(currentData, evaluatorContext)) {
                started = true;
                fireStartEvent(i, currentData);
            }
            fireEvent(new Event(currentData));
        }
        int end = Math.max(rowData.size() - 1, 0);
        if (started) {
            FinishEvent event = new FinishEvent(end);
            event.setPayload(rowData.get(end).toString());
            event.setContext(rowData);
            fireEvent(event);
        }
    }

    private T resolvePreviousData(List<T> rowData, int i) {
        if (i > 0) {
            return rowData.get(i - 1);
        }
        return null;
    }


    private void fireStartEvent(int iter, T value) {
        StartEvent startEvent = new StartEvent(iter);
        startEvent.setPayload(value);
        fireEvent(startEvent);
    }

    private void fireEndEvent(int iter, T currentData, Object context) {
        EndEvent endEvent = new EndEvent(iter);
        endEvent.setPayload(currentData);
        endEvent.setContext(context);
        fireEvent(endEvent);
    }

    private void fireEvent(Event event) {
        for (EventListener eventListener : eventListeners) {
            eventListener.fireEvent(event);
        }
    }
}



