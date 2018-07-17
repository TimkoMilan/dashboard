package com.globallogic.dashboard.evaluator;

public interface StartDataEvaluator<T> {
    boolean isStartData(String currentData, EvaluatorContext<T> rowContext);
}
