package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.repository.VacationRepository;
import com.globallogic.dashboard.to.VacationCreateDto;
import org.springframework.stereotype.Service;

import javax.swing.plaf.metal.MetalMenuBarUI;
import javax.transaction.Transactional;

//todo create facade
@Service
@Transactional
public class VacationService {

    private MemberRepository memberRepository;

    private VacationRepository vacationRepository;

    public VacationService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void createVacation(VacationCreateDto vacationCreateDto) {
        String memberName = vacationCreateDto.getMemberName();
        Member memberByName = null;//memberRepository.findByname- todo dorobit metodu
        Vacation v = new Vacation();
        v.setStart(vacationCreateDto.getStart());
        v.setEnd(vacationCreateDto.getEnd());
        v.setMember(memberByName);

        vacationRepository.save(v);


    }
}
