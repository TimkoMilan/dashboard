package com.globallogic.dashboard.evaluator;

import java.util.List;

public class EvaluatorContext<T> {
    List<T> data;

    public EvaluatorContext(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
