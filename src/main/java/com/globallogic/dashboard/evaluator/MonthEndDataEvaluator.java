package com.globallogic.dashboard.evaluator;

import org.apache.logging.log4j.util.Strings;

public class MonthEndDataEvaluator implements EndDataEvaluator<Integer> {
    @Override
    public boolean isEndData(String currentData, EvaluatorContext<Integer> rowContext) {
        return !Strings.isBlank(currentData);

    }

}
