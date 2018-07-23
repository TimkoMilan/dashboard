package com.globallogic.dashboard.evaluator;

import org.apache.logging.log4j.util.Strings;

public class MonthEndDataEvaluator implements EndDataEvaluator<String> {
    @Override
    public boolean isEndData(String currentData, String previousData, EvaluatorContext<String> rowContext) {
        return !Strings.isBlank(currentData);
    }
}
