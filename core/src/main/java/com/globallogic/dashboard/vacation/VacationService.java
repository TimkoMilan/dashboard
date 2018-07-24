package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;
import com.globallogic.dashboard.loader.DataLoader;
import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.member.MemberRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<VacationDto> getVacationbyMonth(Date startDate, Date endDate) {
        List<Vacation> vacation = vacationRepository.findVacationsByStartIsBetween(startDate, endDate);
        return vacation.stream().map(vacation1 -> {
            VacationDto vacationDto = new VacationDto();
            vacationDto.setFrom(vacation1.getStart());
            vacationDto.setTo(vacation1.getEnd());
            vacationDto.setHalfDay(vacation1.isHalfDay());
            return vacationDto;
        }).collect(Collectors.toList());


    }
}
