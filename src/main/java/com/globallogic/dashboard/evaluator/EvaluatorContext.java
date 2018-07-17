package com.globallogic.dashboard.evaluator;

public class EvaluatorContext<T> {
    T data;

    public EvaluatorContext(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
