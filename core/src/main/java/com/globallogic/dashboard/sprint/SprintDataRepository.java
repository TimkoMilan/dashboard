package com.globallogic.dashboard.sprint;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintDataRepository extends JpaRepository<SprintDataModel,Long> {

    List<SprintDataModel>findAllBySprint_Name(String sprint);
    List<SprintDataModel>findAllByTeam_Name(String teamName);

}
