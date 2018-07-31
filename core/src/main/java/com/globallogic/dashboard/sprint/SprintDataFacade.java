package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintData;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Set;

public interface SprintDataFacade {

    Set<SprintData> getAllSprintData() throws GeneralSecurityException, IOException, ParseException;

}

