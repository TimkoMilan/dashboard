package com.globallogic.dashboard.event;

import org.apache.commons.lang3.Range;

import java.util.List;

public class MonthData {
    private String month;
    private Range<Integer> range;
    private List<String> data;

    public MonthData() {
    }

    public MonthData(String month, Range<Integer> range, List<String> data) {
        this.month = month;
        this.range = range;
        this.data = data;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Range<Integer> getRange() {
        return range;
    }

    public void setRange(Range<Integer> range) {
        this.range = range;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "MonthData{" +
                "month='" + month + '\'' +
                ", range=" + range +
                ", data=" + data +
                '}';
    }
}