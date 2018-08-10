package com.globallogic.dashboard.vacation;

import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
            if (vacationFilterDto.getStart() != null){
                booleanBuilder.and(QVacation.vacation.start.lt(vacationFilterDto.getEnd()).and(QVacation.vacation.end.gt(vacationFilterDto.getStart())));
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

}

