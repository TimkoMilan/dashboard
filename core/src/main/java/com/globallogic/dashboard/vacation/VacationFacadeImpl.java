package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;
import com.globallogic.dashboard.loader.DataLoader;
import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.member.MemberRepository;
import com.globallogic.dashboard.sprint.SprintDataRepository;
import com.globallogic.dashboard.sprint.SprintRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class VacationFacadeImpl implements VacationFacade {

    private DataLoader dataLoader;
    private VacationRepository vacationRepository;
    private MemberRepository memberRepository;
    private SprintRepository sprintRepository;
    private SprintDataRepository sprintDataRepository;


    public VacationFacadeImpl(DataLoader dataLoader, VacationRepository vacationRepository, MemberRepository memberRepository, SprintRepository sprintRepository) {
        this.dataLoader = dataLoader;
        this.vacationRepository = vacationRepository;
        this.memberRepository = memberRepository;
        this.sprintRepository = sprintRepository;
    }

    @Override
    public List<VacationData> getAllVacations() {
        List<VacationData> vacationData = dataLoader.loadVacationData();
        for (VacationData vacationDatum : vacationData) {
            String name = vacationDatum.getName();
            Member mamber =  memberRepository.findMemberByNameIsLike(name);
            Vacation v = new Vacation();
            v.setMember(mamber);
            v.setStart(vacationDatum.getFrom());
            v.setEnd(vacationDatum.getTo());
            v.setHalfDay(vacationDatum.isHalfDay());
            vacationRepository.save(v);
        }
        return vacationData;
    }

    @Override
    public List<Vacation> getAllvacationBySprint(String sprint) {
        Date start = sprintRepository.findByName(sprint).getStart();
        Date end = sprintRepository.findByName(sprint).getEnd();
        return vacationRepository.findVacationsByStartIsBetween(start,end);
    }

    @Override
    public List<VacationDto> getVacationByMemberName(String name) {
        return null;
    }

    @Override
    public List<VacationDto> getVacationByTeam(Long teamid) {
        return null;
    }

    @Override
    public List<VacationDto> getVacationbyMonth(Date startDate, Date endDate) {
        return null;
    }



}
