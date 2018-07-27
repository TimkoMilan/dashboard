package com.globallogic.dashboard.loader;

import com.globallogic.dashboard.event.SprintData;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public interface SprintLoader {

    List<SprintData> loadSprintData() throws GeneralSecurityException, IOException;

}
