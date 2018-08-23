package com.globallogic.dashboard.fte;

import com.globallogic.dashboard.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FteRepository extends JpaRepository<Fte, Long> {

    List<Fte> findByTeamAndMonth(Team team, Byte month);
}
