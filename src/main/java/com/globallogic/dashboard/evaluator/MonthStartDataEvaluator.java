package com.globallogic.dashboard.evaluator;

import org.apache.logging.log4j.util.Strings;

public class MonthStartDataEvaluator implements StartDataEvaluator<String> {
    @Override
    public boolean isStartData(String currentData, EvaluatorContext<String> rowContext) {
        return !Strings.isBlank(currentData);
    }
}
