package com.globallogic.dashboard.old;


import com.globallogic.dashboard.evaluator.*;
import com.globallogic.dashboard.event.*;

import java.util.List;

public class MonthTest {

    public static final String[] rowData = {"", "", "",
            "January", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
            "February", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28",
            "March", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12", "13", "14", "15", "16", "17", "18", "19", "20",
            "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
    };

    private String month;
    private int from, to;
    private List<String> data;


    public MonthTest(String month, int from, int to, List<String> data) {
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
        StartDataEvaluator<Integer> startDataEvaluator = new MonthStartDataEvaluator();
        EndDataEvaluator<Integer> endDataEvaluator = new MonthEndDataEvaluator();
        EvaluatorContext<Integer> rowContext = new EvaluatorContext<>(null);
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
        EndEvent endEvent = new EndEvent(i - 1);
        eventListener.fireEvent(endEvent);
    }

    private static void foundStartData(EventListener eventListener, int i, String arrMonth) {
        StartEvent event = new StartEvent(i);
        event.setPayload(arrMonth);
        eventListener.fireEvent(event);
    }

    private static boolean isMonthNameFound(String arrMonth) {
        return arrMonth.length() > 3;
    }
}
