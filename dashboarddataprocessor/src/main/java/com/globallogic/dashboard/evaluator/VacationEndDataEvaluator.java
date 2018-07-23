package com.globallogic.dashboard.evaluator;

public class VacationEndDataEvaluator implements EndDataEvaluator<String> {

    @Override
    public boolean isEndData(String currentData, String previousData, EvaluatorContext<String> rowContext) {
        return currentData == null || !currentData.equals(previousData);
    }

}
