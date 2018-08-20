package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;
import com.globallogic.dashboard.loader.DataLoader;
import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.member.MemberService;
import com.globallogic.dashboard.sprint.SprintDto;
import com.globallogic.dashboard.sprint.SprintService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VacationFacadeImpl implements VacationFacade {

    private DataLoader dataLoader;
    private MemberService memberservice;
    private VacationService vacationService;
    private SprintService sprintService;

    public VacationFacadeImpl(DataLoader dataLoader, MemberService memberservice, VacationService vacationService, SprintService sprintService) {
        this.dataLoader = dataLoader;
        this.memberservice = memberservice;
        this.vacationService = vacationService;
        this.sprintService = sprintService;
    }

    @Override
    public void loadVacation() {
        List<VacationData> vacationData = dataLoader.loadVacationData();
        vacationService.deleteAllVacation();
        for (VacationData vacationDatum : vacationData) {
            String name = vacationDatum.getName();
            Member mamber = memberservice.findMemberByMemberName(name);
            Vacation v = new Vacation();
            v.setMember(mamber);
            v.setStart(vacationDatum.getFrom());
            v.setEnd(vacationDatum.getTo());
            v.setHalfDay(vacationDatum.isHalfDay());
            vacationService.saveVacation(v);
        }
    }

    @Override
    public List<VacationDto> getVacations(VacationFilterDto vacationFilterDto) {
            if (vacationFilterDto.getSprintId() != null) {
                SprintDto sprint = sprintService.findById(Long.valueOf(vacationFilterDto.getSprintId()));
                vacationFilterDto.setStart(sprint.getStart());
                vacationFilterDto.setEnd(sprint.getEnd());
            }
        return vacationService.getVacations(vacationFilterDto);
    }


}
