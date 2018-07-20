package com.globallogic.dashboard.service;

import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.event.VacationEventListener;
import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.repository.VacationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(VacationEventListener.class);


    public VacationService(MemberRepository memberRepository, VacationRepository vacationRepository, DataLoader dataLoader) {
        this.memberRepository = memberRepository;
        this.vacationRepository = vacationRepository;
        this.dataLoader = dataLoader;
    }



    public List<VacationDto> getAllVacations() {
        List<VacationDto> vacationDtos = dataLoader.loadVacationData();
        for (VacationDto vacationDto : vacationDtos) {
            String name = vacationDto.getName();
            Member mamber = (Member) memberRepository.findMemberByNameIsLike(name);
            Vacation v = new Vacation();
            v.setMember(mamber);
            v.setStart(vacationDto.getFrom());
            v.setEnd(vacationDto.getTo());
            v.setHalfDay(vacationDto.isHalfDay());
            vacationRepository.save(v);
        }
        return vacationDtos;
    }

    public List<Vacation> getVacationByName(String name) {
        name =name.replace(" ","").toLowerCase();
        return vacationRepository.findVacationsByMember_SearchString(name);

    }
    public List<Vacation> getVacationByTeam(Long teamid){
        return vacationRepository.findVacationsByMember_TeamId(teamid);
    }

}
