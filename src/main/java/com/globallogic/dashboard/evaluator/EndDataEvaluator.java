package com.globallogic.dashboard.evaluator;

public interface EndDataEvaluator<T> {
    boolean isEndData(String currentData, EvaluatorContext<T> rowContext);
}
