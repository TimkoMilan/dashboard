package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.common.VacationException;
import com.globallogic.dashboard.event.VacationData;
import com.globallogic.dashboard.loader.DataLoader;
import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.member.MemberService;
import com.globallogic.dashboard.sprint.SprintService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public void loadVacation(){
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
        return vacationService.getVacations(vacationFilterDto);

    }

    @Override
    @Transactional(readOnly = true)
    public List<VacationDto> getAllVacationBySprint(String sprint) {
        Date start = sprintService.findStartBySprintName(sprint).getStart();
        Date end = sprintService.findEndBySprintName(sprint).getEnd();
        List<Vacation> vacations = vacationService.findVacationByDate(start, end);
        if (vacations.isEmpty()) {
            return vacations.stream().map(VacationUtil::convertToDto).collect(Collectors.toList());
        } else {
            throw new VacationException("Vacation between these days not found", start, end);
        }

    }

    @Override
    public List<VacationDto> getVacationByMemberName(String name) {
        return vacationService.getVacationByMemberName(name);
    }

    @Override
    public List<VacationDto> getVacationByTeam(Long teamid) {
        return vacationService.getVacationByTeam(teamid);
    }

    @Override
    public List<VacationDto> getVacationByMonth(Date startDate, Date endDate) {
        return vacationService.getVacationbyMonth(startDate, endDate);
    }


}
