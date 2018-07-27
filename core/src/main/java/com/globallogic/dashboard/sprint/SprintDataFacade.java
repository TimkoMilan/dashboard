package com.globallogic.dashboard.sprint;

import com.globallogic.dashboard.event.SprintData;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface SprintDataFacade {

    List<SprintData> getAllSprintData() throws GeneralSecurityException, IOException;

}

