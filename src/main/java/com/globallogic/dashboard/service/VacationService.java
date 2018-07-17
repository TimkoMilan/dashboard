package com.globallogic.dashboard.service;

import com.globallogic.dashboard.VacationDto;
import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.old.GoogleDataLoader;
import com.globallogic.dashboard.repository.MemberRepository;
import com.globallogic.dashboard.repository.VacationRepository;
import com.globallogic.dashboard.to.VacationCreateDto;
import org.springframework.stereotype.Service;

import javax.swing.plaf.metal.MetalMenuBarUI;
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
        return dataLoader.loadVacationData();
    }
}
