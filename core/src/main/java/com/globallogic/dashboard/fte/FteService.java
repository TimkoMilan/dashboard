package com.globallogic.dashboard.fte;

import com.globallogic.dashboard.team.Team;
import org.springframework.stereotype.Service;


import java.util.List;

public interface FteService {

    List<Fte> findFteByTeamAndMonth(Team team, Byte month);

    Fte createFte(Fte fte);
}
