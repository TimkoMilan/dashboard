package com.globallogic.dashboard.evaluator;

public interface StartDataEvaluator<T> {
    boolean isStartData(T currentData, EvaluatorContext<T> rowContext);
}
