package com.globallogic.dashboard.vacation;

import com.querydsl.core.BooleanBuilder;
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

    public List<VacationDto> getVacations(VacationFilterDto vacationFilterDto) {
        if (vacationFilterDto == null) {
            List<Vacation> vacations = vacationRepository.findAll();
            return VacationUtil.convertToListDto(vacations);
        } else {
            BooleanBuilder booleanBuilder = new BooleanBuilder();
            if (vacationFilterDto.getMemberId() != null) {
                booleanBuilder.and(QVacation.vacation.member.id.eq(vacationFilterDto.getMemberId()));
            }
            if (vacationFilterDto.getTeamId() != null) {
                booleanBuilder.and(QVacation.vacation.member.team.id.eq(vacationFilterDto.getTeamId()));
            }
            return VacationUtil.convertToListDto(vacationRepository.findAll(booleanBuilder.getValue()));
        }
    }

    public void saveVacation(Vacation v) {
        vacationRepository.save(v);
    }

    public void deleteAllVacation() {
        vacationRepository.deleteAll();
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

    public List<Vacation> findVacationByDate(Date start, Date end) {
        return vacationRepository.findVacationsByStartIsBetween(start, end);
    }


}

