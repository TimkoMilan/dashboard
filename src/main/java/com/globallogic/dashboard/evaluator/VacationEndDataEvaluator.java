package com.globallogic.dashboard.evaluator;

import com.google.common.primitives.Floats;
import org.apache.logging.log4j.util.Strings;

public class VacationEndDataEvaluator implements EndDataEvaluator<Boolean> {
    @Override
    public boolean isEndData(String currentData, EvaluatorContext<Boolean> rowContext) {
        return Strings.isBlank(currentData) || (Floats.tryParse(currentData)!=null && Float.valueOf(currentData).equals(0.5));

    }
}
