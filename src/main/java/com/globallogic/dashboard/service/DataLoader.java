package com.globallogic.dashboard.service;

import com.globallogic.dashboard.to.MemberCreateDto;
import com.globallogic.dashboard.to.VacationCreateDto;

import java.util.List;

public interface DataLoader {

    List<MemberCreateDto> loadData();

    List<VacationCreateDto> loadVacationData();
}
