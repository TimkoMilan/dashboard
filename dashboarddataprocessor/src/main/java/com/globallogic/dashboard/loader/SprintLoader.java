package com.globallogic.dashboard.loader;

import com.globallogic.dashboard.event.SprintGeneratedData;

import java.util.Set;

public interface SprintLoader {


    Set<SprintGeneratedData> loadSprintData();

}
