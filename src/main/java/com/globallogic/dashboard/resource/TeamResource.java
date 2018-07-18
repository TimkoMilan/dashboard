package com.globallogic.dashboard.resource;


import com.globallogic.dashboard.model.Team;
import com.globallogic.dashboard.service.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping("team")
public class TeamResource {

    @Autowired
    private DataLoader dataLoader;

    @GetMapping
    public List<Team> getAllTeam() throws GeneralSecurityException, IOException {

        return dataLoader.loadData();
    }

}
