package com.globallogic.dashboard.processor;

import com.globallogic.dashboard.evaluator.MonthEndDataEvaluator;
import com.globallogic.dashboard.evaluator.MonthStartDataEvaluator;
import com.globallogic.dashboard.evaluator.VacationEndDataEvaluator;
import com.globallogic.dashboard.evaluator.VacationStartDataEvaluator;
import com.globallogic.dashboard.event.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ProcessorTest {

    public static final String[] monthData = {
            "January", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "",
            "February", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "",
            "March", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "", "",
    };

    //first 3 are offset
    public static final String[] vacationData = {
            "", "", "0.5", "", "1", "1", "1", "", "", "", "", "", "",
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
        Processor<String> monthProcessor = new Processor<>(new MonthStartDataEvaluator(), new MonthEndDataEvaluator());
        MonthEventListener monthEventListener = new MonthEventListener();
        monthProcessor.withEventListener(monthEventListener);
        monthProcessor.process(Arrays.asList(monthData));
        List<MonthData> monthData = monthEventListener.getMonthData();

        List<Object> days = new ArrayList<>();

        for (int i = 0; i < 92; i++) {
            days.add(i);
        }

        Processor<String> vacationProcessor = new Processor<>(new VacationStartDataEvaluator(), new VacationEndDataEvaluator());
        MonthUtil monthUtil = new MonthUtil(monthData);
        monthUtil.setDays(days);
        VacationEventListener vacationEventListener = new VacationEventListener(monthUtil);
        vacationProcessor.withEventListener(vacationEventListener);

        vacationProcessor.process(Arrays.asList(vacationData));

        Assert.assertEquals(vacationEventListener.getVacationData().size(), 5);


    }


}