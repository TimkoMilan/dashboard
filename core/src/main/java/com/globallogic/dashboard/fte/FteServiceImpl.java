package com.globallogic.dashboard.fte;


import com.globallogic.dashboard.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FteServiceImpl implements FteService{

    @Autowired
    private FteRepository fteRepository;

    @Override
    public List<Fte> findFteByTeamAndMonth(Team team, Byte month){
        return fteRepository.findByTeamAndMonth(team, month);
    }

    @Override
    public Fte createFte(Fte fte) {
        return fteRepository.save(fte);
    }


}
