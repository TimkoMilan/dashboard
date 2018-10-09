package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.member.Member;
import com.globallogic.dashboard.security.JwtTokenProvider;
import com.globallogic.dashboard.user.Role;
import com.globallogic.dashboard.user.User;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("sprintdata")
public class SprintDataResource {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private SprintDataFacade sprintDataFacade;

    public SprintDataResource(SprintDataFacade sprintDataFacade) {
        this.sprintDataFacade = sprintDataFacade;
    }

    @GetMapping
    public List<SprintDataDto> getAllSprintData(@RequestParam(required = false) String teamId, @RequestParam(required = false) String sprintId,ServletRequest req) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SprintDataFilterDto sprintDataFilterDto = new SprintDataFilterDto();
        sprintDataFilterDto.setSprintId(sprintId);

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) req);
        Integer memberTeamId=jwtTokenProvider.getTeamId(token);

        for (Role role : user.getRoles()) {
            if (role.getAuthority().equals("ROLE_ADMIN")){
                sprintDataFilterDto.setTeamId(teamId);
                return sprintDataFacade.getAllSprintData(sprintDataFilterDto);
            }
            else {
                sprintDataFilterDto.setTeamId(String.valueOf(memberTeamId));
            }
        }
        return sprintDataFacade.getAllSprintData(sprintDataFilterDto);
    }

    @GetMapping("/loadData")//loading data from excel
    public void loadData() {
        sprintDataFacade.loadSprintData();
    }

    @GetMapping("teams/{teamName}")
    public List<SprintDataDto> getAllSprintDataByTeam(@PathVariable(value = "teamName") @NotNull String teamName) {
        return sprintDataFacade.getAllSprintDataByTeam(teamName);
    }

}
