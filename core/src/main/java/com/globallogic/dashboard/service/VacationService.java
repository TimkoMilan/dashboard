package com.globallogic.dashboard.service;

import com.globallogic.dashboard.event.VacationData;
import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.repository.VacationRepository;
import com.globallogic.dashboard.VacationDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

//todo create facade
@Service
@Transactional
public class VacationService {

    private MemberRepository memberRepository;

    private VacationRepository vacationRepository;

    private DataLoader dataLoader;

    public VacationService(MemberRepository memberRepository, VacationRepository vacationRepository, DataLoader dataLoader) {
        this.memberRepository = memberRepository;
        this.vacationRepository = vacationRepository;
        this.dataLoader = dataLoader;
    }


    public List<VacationData> getAllVacations() {
        List<VacationData> vacationData = dataLoader.loadVacationData();
        for (VacationData vacationDatum : vacationData) {
            String name = vacationDatum.getName();
            Member mamber = (Member) memberRepository.findMemberByNameIsLike(name);
            Vacation v = new Vacation();
            v.setMember(mamber);
            v.setStart(vacationDatum.getFrom());
            v.setEnd(vacationDatum.getTo());
            v.setHalfDay(vacationDatum.isHalfDay());
            vacationRepository.save(v);
        }
        return vacationData;
    }

    public List<Vacation> getVacationByName(String name) {
        name = name.replace(" ", "").toLowerCase();
        return vacationRepository.findVacationsByMember_SearchString(name);

    }

    public List<Vacation> getVacationByTeam(Long teamid) {
        return vacationRepository.findVacationsByMember_TeamId(teamid);
    }

}
