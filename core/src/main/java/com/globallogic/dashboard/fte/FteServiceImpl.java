package com.globallogic.dashboard.fte;


import com.globallogic.dashboard.team.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FteServiceImpl implements FteService{

    @Autowired
    private FteRepository fteRepository;

    @Override
    public Double findFteByTeamAndMonthAndYear(Team team, Byte month, Integer year){
        if (fteRepository.findByTeamAndMonthAndYear(team, month, year)!= null){
            return fteRepository.findByTeamAndMonthAndYear(team, month, year).getFte();
        }else {
            return 0.0;
        }
    }

    @Override
    public Fte createFte(Fte fte) {
        return fteRepository.save(fte);
    }


}
