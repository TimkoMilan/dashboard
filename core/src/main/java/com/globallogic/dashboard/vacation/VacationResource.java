package com.globallogic.dashboard.vacation;

import com.globallogic.dashboard.security.JwtTokenProvider;
import com.globallogic.dashboard.user.Role;
import com.globallogic.dashboard.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("vacation")
public class VacationResource {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private VacationFacade vacationFacade;

    public VacationResource(VacationFacade vacationFacade) {
        this.vacationFacade = vacationFacade;
    }

    @GetMapping("loadVacationData")
    public void loadVacation() {
        vacationFacade.loadVacation();
    }

    @GetMapping
    public List<VacationDto> getVacations(@RequestParam(required = false) String memberId,
                                          @RequestParam(required = false) String teamId,
                                          @RequestParam(required = false) String sprintId,
                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                  Date startDate,
                                          @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                  Date endDate,
                                          ServletRequest req
    ) {//
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        VacationFilterDto vacationFilterDto = new VacationFilterDto();
        vacationFilterDto.setMemberId(memberId);
        vacationFilterDto.setSprintId(sprintId);
        vacationFilterDto.setStart(startDate);
        vacationFilterDto.setEnd(endDate);
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        Integer memberTeamId=jwtTokenProvider.getTeamId(token);
        for (Role role : user.getRoles()) {
            if (role.getAuthority()=="ROLE_ADMIN"){
                vacationFilterDto.setTeamId(teamId);
                return vacationFacade.getVacations(vacationFilterDto);

            }
            else {
                vacationFilterDto.setTeamId(String.valueOf(memberTeamId));
            }
        }

        return vacationFacade.getVacations(vacationFilterDto);
    }


}
