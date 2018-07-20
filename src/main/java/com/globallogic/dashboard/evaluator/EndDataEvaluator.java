package com.globallogic.dashboard.evaluator;

public interface EndDataEvaluator<T> {
    boolean isEndData(T currentData, T previousData, EvaluatorContext<T> rowContext);
}
