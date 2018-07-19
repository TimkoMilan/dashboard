package com.globallogic.dashboard.service;

import com.globallogic.dashboard.VacationDto;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface DataLoader {

    void loadData() throws GeneralSecurityException, IOException;

    List<VacationDto> loadVacationData();
}
