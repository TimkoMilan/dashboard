package com.globallogic.dashboard.service;

import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.model.Team;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface DataLoader {

    List<Team> loadData() throws GeneralSecurityException, IOException;

    List<VacationDto> loadVacationData();
}
