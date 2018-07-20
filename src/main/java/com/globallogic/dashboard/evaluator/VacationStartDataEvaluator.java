package com.globallogic.dashboard.evaluator;

import com.google.common.primitives.Floats;

public class VacationStartDataEvaluator implements StartDataEvaluator<String> {
    @Override
    public boolean isStartData(String currentData, EvaluatorContext<String> evaluatorContext) {
        return currentData != null && Floats.tryParse(currentData) != null;
    }
}
