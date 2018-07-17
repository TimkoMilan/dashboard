package com.globallogic.dashboard.service;

import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.to.MemberCreateDto;

import java.util.List;

public interface DataLoader {

    List<MemberCreateDto> loadData();

    List<VacationDto> loadVacationData();
}
