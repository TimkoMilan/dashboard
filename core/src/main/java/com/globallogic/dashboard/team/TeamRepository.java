package com.globallogic.dashboard.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    Team findTeamByNameIgnoreCase(String name);

    Optional<Team> findById(Long id);

    Team findTeamById(Long teamId);
}
