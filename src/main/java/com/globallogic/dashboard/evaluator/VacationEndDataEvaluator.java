package com.globallogic.dashboard.evaluator;

import org.apache.logging.log4j.util.Strings;

public class VacationEndDataEvaluator implements EndDataEvaluator<Boolean> {
    @Override
    public boolean isEndData(String currentData, EvaluatorContext<Boolean> rowContext) {
        return Strings.isBlank(currentData);

    }
}
