package com.globallogic.dashboard;

import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.to.TeamCreateDto;

public class TeamUtil {
    private TeamUtil() {
    }

    public static Team createTeamFromTeamCreateDto(TeamCreateDto teamCreateDto) {
        Team team = new Team();
        team.setProjectName(teamCreateDto.getName());
        return team;
    }


}
