package com.globallogic.dashboard.fte;

import com.globallogic.dashboard.team.Team;

public interface FteService {

    Double findFteByTeamAndMonth(Team team, Byte monthByte);

    Fte createFte(Fte fte);
}
