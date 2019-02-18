package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.event.VacationData;
import com.globallogic.dashboard.loader.DataLoader;
import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.member.MemberRepository;
import com.globallogic.dashboard.member.MemberService;
import com.globallogic.dashboard.member.MemberUtil;
import com.globallogic.dashboard.sprint.SprintDto;
import com.globallogic.dashboard.sprint.SprintService;
import com.globallogic.dashboard.team.Team;
import com.globallogic.dashboard.team.TeamService;
import com.globallogic.dashboard.team.TeamUtil;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class VacationFacadeImpl implements VacationFacade {

    private DataLoader dataLoader;
    private MemberService memberService;
    private VacationService vacationService;
    private SprintService sprintService;
    private TeamService teamService;
    private MemberRepository memberRepository;

    public VacationFacadeImpl(DataLoader dataLoader, MemberService memberService, VacationService vacationService,
                              SprintService sprintService, TeamService teamService, MemberRepository memberRepository) {
        this.dataLoader = dataLoader;
        this.memberService = memberService;
        this.vacationService = vacationService;
        this.sprintService = sprintService;
        this.teamService = teamService;
        this.memberRepository = memberRepository;
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

    @Override
    public void loadVacation() {
        List<VacationData> vacationData = dataLoader.loadVacationData();
        vacationService.deleteAllVacation();
        for (VacationData vacationDatum : vacationData) {
            String name = vacationDatum.getName();
            Member member = memberService.findMemberBySearchString(MemberUtil.toSearchString(name));
            if (member == null) {
                member = createNewMember(vacationDatum);
            } else {
                checkForChange(member, vacationDatum);
            }
            Vacation v = new Vacation();
            v.setMember(member);
            v.setStart(vacationDatum.getFrom());
            v.setHalfDay(vacationDatum.isHalfDay());
            vacationService.saveVacation(v);
        }
    }

    private Member createNewMember(VacationData vacationDatum) {
        Member member = new Member();
        member.setName(vacationDatum.getName());
        member.setPosition(vacationDatum.getPosition());
        member.setSearchString(MemberUtil.toSearchString(member.getName()));
        String teamName = TeamUtil.processTeamNameString(vacationDatum.getTeamName());
        Team teamByName = teamService.findByTeamName(teamName);

        if (teamByName == null) {
            System.out.println();
        }

        member.setTeam(teamByName);
        member = memberRepository.save(member);
        return member;
    }


    private void checkForChange(Member member, VacationData vacationDatum) {
        Boolean hasChanged = false;
        if (!Strings.isNullOrEmpty(vacationDatum.getPosition()) &&
                !vacationDatum.getPosition().equals(member.getPosition())) {
            member.setPosition(vacationDatum.getPosition());
            hasChanged = true;
        }
        if (!Objects.equals(vacationDatum.getTeamName(), member.getTeam().getName())) {
            hasChanged = true;
        }
        if (hasChanged) {
            memberRepository.save(member);
        }
    }
}
