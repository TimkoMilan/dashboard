package com.globallogic.dashboard.event;

import org.apache.commons.lang3.Range;

public class SprintData {

    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SprintData{" +
                "name='" + name + '\'' +
                '}';
    }
}
