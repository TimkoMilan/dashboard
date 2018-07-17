package com.globallogic.dashboard.evaluator;

import org.apache.logging.log4j.util.Strings;

public class MonthStartDataEvaluator implements StartDataEvaluator<Integer> {
    @Override
    public boolean isStartData(String currentData, EvaluatorContext<Integer> rowContext) {
        return !Strings.isBlank(currentData);
    }
}
