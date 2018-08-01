package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VacationService {

    private VacationRepository vacationRepository;


    public VacationService(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    public List<VacationData> getAllVacations() {
        return null;
    }

    public List<VacationDto> getVacationByMemberName(String name) {
         name = name.replace(" ", "").toLowerCase();
         List<Vacation> vacation = vacationRepository.findVacationsByMember_SearchString(name);
        return vacation.stream().map(VacationUtil::convertToDto).collect(Collectors.toList());
    }

    public List<VacationDto> getVacationByTeam(Long teamid) {
        List<Vacation> vacation = vacationRepository.findVacationsByMember_TeamId(teamid);
        return vacation.stream().map(VacationUtil::convertToDto).collect(Collectors.toList());
    }

    public List<VacationDto> getVacationbyMonth(Date startDate, Date endDate) {
        List<Vacation> vacation = vacationRepository.findVacationsByStartIsBetween(startDate, endDate);
        return vacation.stream().map(VacationUtil::convertToDto).collect(Collectors.toList());
    }
}
