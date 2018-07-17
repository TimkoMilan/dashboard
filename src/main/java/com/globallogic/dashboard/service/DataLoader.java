package com.globallogic.dashboard.service;

import com.globallogic.dashboard.VacationDto;

import java.util.List;

public interface DataLoader {

    void loadData();

    List<VacationDto> loadVacationData();
}
