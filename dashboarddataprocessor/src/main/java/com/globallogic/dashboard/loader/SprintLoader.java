package com.globallogic.dashboard.loader;

import com.globallogic.dashboard.event.SprintData;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.Set;

public interface SprintLoader {


    //todo remove exception here and wherever this interface is being used, cover with one layer-specific one ie DataLoadingException
    Set<SprintData> loadSprintData() throws GeneralSecurityException, IOException, ParseException;

}
