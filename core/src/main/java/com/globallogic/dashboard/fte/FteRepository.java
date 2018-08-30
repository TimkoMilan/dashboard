package com.globallogic.dashboard.fte;

import com.globallogic.dashboard.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FteRepository extends JpaRepository<Fte, Long> {

    Fte findByTeamAndMonthAndYear(Team team, Byte month, Integer year);
}
