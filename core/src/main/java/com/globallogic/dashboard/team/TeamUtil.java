package com.globallogic.dashboard.team;

public class TeamUtil {
    private TeamUtil() {
    }

    public static Team createTeamFromTeamCreateDto(TeamCreateDto teamCreateDto) {
        Team team = new Team();
        team.setName(teamCreateDto.getName());

        return team;
    }

    public static TeamDto convertToDto(Team team){
        TeamDto teamDto = new TeamDto();

        teamDto.setTeamName(team.getName());
        teamDto.setFocus(team.getFocus());
        teamDto.setSprint(team.getSprintDatumModels());
        teamDto.setMembers(team.getMembers());
        teamDto.setId(team.getId());
        teamDto.setFte(team.getFte());
        return  teamDto;
    }
    public static TeamNameDto convertToTeamNameDto(Team team){
        TeamNameDto teamNameDto = new TeamNameDto();
        teamNameDto.setId(team.getId());
        teamNameDto.setTeamName(team.getName());
        teamNameDto.setFocus(team.getFocus());
        return teamNameDto;
    }
}
