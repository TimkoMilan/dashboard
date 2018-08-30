package com.globallogic.dashboard.fte;

import com.globallogic.dashboard.team.Team;

public interface FteService {

    Double findFteByTeamAndMonthAndYear(Team team, Byte monthByte, Integer Year);

    Fte createFte(Fte fte);
}
