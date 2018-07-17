package com.globallogic.dashboard.old;

import com.globallogic.dashboard.evaluator.*;
import com.globallogic.dashboard.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

public class VacationTest {
    private static final Logger log = LoggerFactory.getLogger(VacationTest.class);
    public static final String[] rowData = {
            "", "1", "1", "1", "", "", "", "", "", "",
            "", "", "1", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "",
            "", "1", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "1", "1", "", "", "", "", "", "", "", "", "",
    };

    private String month;
    private int from, to;
    private List<String> data;


    public VacationTest(String month, int from, int to, List<String> data) {
        this.month = month;
        this.from = from;
        this.to = to;
        this.data = data;
    }

    @Override
    public String toString() {
        return "MonthTest{" +
                "month='" + month + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", data=" + data +
                '}';
    }


    //todo genericky rowProcessor - startDataFound, endDataFound, rowFinished + kontext (napr na firstFoundMonth).
// startDataEvaluator
// endDataEvaluator
// finishRowEvaluator
    public static void main(String[] args) {

        EventListener eventListener = new MonthEventListener();
        int i = 0;
        boolean firstFoundMonth = true;
        StartDataEvaluator<Boolean> startDataEvaluator = new VacationStartDataEvaluator();
        EndDataEvaluator<Boolean> endDataEvaluator = new VacationEndDataEvaluator();

        EvaluatorContext<Boolean> rowContext = new EvaluatorContext<>(null);
        for (String cell : rowData) {
            if (endDataEvaluator.isEndData(cell, rowContext)) {
                foundEndData(eventListener, i);
            }
            if (startDataEvaluator.isStartData(cell, rowContext)) {
                foundStartData(eventListener, i, cell);
            }
            eventListener.fireEvent(new Event(cell));
            i++;
        }
        FinishEvent finishEvent = new FinishEvent(i);
        eventListener.fireEvent(finishEvent);
    }

    private static void foundEndData(EventListener eventListener, int i) {
        log.info("Found vacation end{}", i);
        EndEvent endEvent = new EndEvent(i - 1);
        eventListener.fireEvent(endEvent);
    }

    private static void foundStartData(EventListener eventListener, int i, String arrMonth) {
        log.info("Found vacation start{}", i);
        StartEvent event = new StartEvent(i);
        event.setPayload(arrMonth);
        eventListener.fireEvent(event);
    }

    private static boolean isMonthNameFound(String arrMonth) {
        return arrMonth.length() > 3;
    }
}
