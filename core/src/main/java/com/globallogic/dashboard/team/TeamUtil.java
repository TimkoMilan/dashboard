package com.globallogic.dashboard.team;

public class TeamUtil {
    private TeamUtil() {
    }

    public static Team createTeamFromTeamCreateDto(TeamCreateDto teamCreateDto) {
        Team team = new Team();
        team.setName(teamCreateDto.getName());
        return team;
    }


}
