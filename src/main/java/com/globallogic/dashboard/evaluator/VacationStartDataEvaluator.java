package com.globallogic.dashboard.evaluator;

import com.google.common.primitives.Floats;

public class VacationStartDataEvaluator implements StartDataEvaluator<Boolean> {
    @Override
    public boolean isStartData(String currentData, EvaluatorContext<Boolean> evaluatorContext) {
        return currentData != null && Floats.tryParse(currentData) != null;
    }
}
