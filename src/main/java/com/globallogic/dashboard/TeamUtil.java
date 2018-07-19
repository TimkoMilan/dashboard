package com.globallogic.dashboard;

import com.globallogic.dashboard.event.VacationEventListener;
import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.to.TeamCreateDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeamUtil {
    public TeamUtil() {
    }
    private static final Logger log = LoggerFactory.getLogger(VacationEventListener.class);

    public static Team createTeamFromTeamCreateDto(TeamCreateDto teamCreateDto) {
        Team team = new Team();
        team.setProjectName(teamCreateDto.getName());
        return team;
    }


}
