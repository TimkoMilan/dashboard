package com.globallogic.dashboard;

import com.globallogic.dashboard.evaluator.MonthEndDataEvaluator;
import com.globallogic.dashboard.evaluator.MonthStartDataEvaluator;
import com.globallogic.dashboard.evaluator.VacationEndDataEvaluator;
import com.globallogic.dashboard.evaluator.VacationStartDataEvaluator;
import com.globallogic.dashboard.event.LoggingListener;
import com.globallogic.dashboard.event.MonthEventListener;
import com.globallogic.dashboard.event.VacationEventListener;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;


public class ProcessorTest {

    public static final String[] monthData = {"", "", "",
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

    //first 3 are offset
    public static final String[] vacationData = {
            "","","","", "1", "1", "1", "", "", "", "", "", "",
            "", "", "1", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "",
            "", "1", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "1", "1", "", "", "", "", "", "", "", "", "",
    };


    @Test
    public void testProcessor() {
        Processor<Integer> monthProcessor = new Processor<>(new MonthStartDataEvaluator(), new MonthEndDataEvaluator());
        MonthEventListener monthEventListener = new MonthEventListener();
        monthProcessor.withEventListener(monthEventListener);
        monthProcessor.process(Arrays.asList(monthData));
        List<MonthDto> monthDtos = monthEventListener.getMonthDtos();

        Processor<Boolean> vacationProcessor = new Processor<>(new VacationStartDataEvaluator(), new VacationEndDataEvaluator());
        vacationProcessor
                .withEventListener(new VacationEventListener(new MonthUtil(monthDtos)))
                .withEventListener(new LoggingListener());

        vacationProcessor.process(Arrays.asList(vacationData));
    }


}