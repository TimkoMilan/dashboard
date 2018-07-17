package com.globallogic.dashboard;

import com.globallogic.dashboard.evaluator.EndDataEvaluator;
import com.globallogic.dashboard.evaluator.EvaluatorContext;
import com.globallogic.dashboard.evaluator.StartDataEvaluator;
import com.globallogic.dashboard.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Processor<T> {
    private StartDataEvaluator<T> startDataEvaluator;
    private EndDataEvaluator<T> endDataEvaluator;
    private Set<EventListener> eventListeners = new HashSet<>();
    private static final Logger log = LoggerFactory.getLogger(Processor.class);


    public Processor(StartDataEvaluator<T> startDataEvaluator, EndDataEvaluator<T> endDataEvaluator) {
        this.startDataEvaluator = startDataEvaluator;
        this.endDataEvaluator = endDataEvaluator;
    }

    public Processor withEventListener(EventListener eventListener) {
        eventListeners.add(eventListener);
        return this;
    }

    public void process(List<Object> rowData) {
        EvaluatorContext<T> evaluatorContext = new EvaluatorContext<>(null);
        boolean started = false;
        for (int i = 0; i < rowData.size(); i++) { //not using foreach, as i need iter
            String currentData = (String) rowData.get(i);
            if (started && endDataEvaluator.isEndData(currentData, evaluatorContext)) {
                started = false;
                fireEndEvent(i - 1, rowData);
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
            event.setContext(rowData);
            fireEvent(event);
        }
    }

    private void fireStartEvent(int iter, String value) {
        log.info("Start event fired for iter: {} with value: {}.", iter, value);
        StartEvent startEvent = new StartEvent(iter);
        startEvent.setPayload(value);
        fireEvent(startEvent);
    }

    private void fireEndEvent(int iter, Object context) {
        log.info("End event fired for iter: {}.", iter);
        EndEvent endEvent = new EndEvent(iter);
        endEvent.setContext(context);
        fireEvent(endEvent);
    }

    private void fireEvent(Event event) {
        for (EventListener eventListener : eventListeners) {
            eventListener.fireEvent(event);
        }
    }
}



