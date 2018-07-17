package com.globallogic.dashboard.service;

import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.repository.VacationRepository;
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

    public List<VacationDto> getAllVacations() {
        List<VacationDto> vacationDtos = dataLoader.loadVacationData();
        for (VacationDto vacationDto : vacationDtos) {
            String name = vacationDto.getName();
            Member mamber = memberRepository.findMemberByNameIsLike(name);
            Vacation v = new Vacation();
            v.setMember(mamber);
            v.setStart(v.getStart());
            v.setEnd(v.getEnd());
            vacationRepository.save(v);
        }
        return vacationDtos;
    }

    public List<Vacation> getVacationByName(String name) {
        return vacationRepository.findVacationsByMember_Name(name);
    }
}
