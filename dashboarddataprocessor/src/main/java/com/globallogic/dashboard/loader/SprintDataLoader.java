package com.globallogic.dashboard.loader;

import com.globallogic.dashboard.event.SprintData;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SprintDataLoader {
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "src/main/resources/credentials";
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CLIENT_SECRET_DIR = "/client_secret.json";
    private static final Logger log = LoggerFactory.getLogger(GoogleDataLoader.class);
    public static final String CONFIG_PROPERTIES = "config.properties";


    private static Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {

        InputStream in = GoogleDataLoader.class.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    public List<SprintData> loadSprintData() throws GeneralSecurityException, IOException {
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        final String spreadsheetId = "1w0BHwyNqRHRfsAQGqhGngvxHN1YvLfuL17VMpiSu1Es";
        final String sprintRange = "reliability!C1:AX1";
        final String dataRange = "reliability!B3:AX7";
        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, sprintRange)
                .execute();

        ValueRange dataResponse = service.spreadsheets().values()
                .get(spreadsheetId, dataRange)
                .execute();

        List<List<Object>> values = response.getValues();
        List<List<Object>> dataValues = dataResponse.getValues();

        if (values == null || values.isEmpty()) {
        } else {
            List<Object> row = values.get(0);
            Map<Integer, String> sprintData = new HashMap<>();
            for (int i = 0; i <= row.size(); i += 2) {
                String sprintName = (String) row.get(i);
                sprintData.put(i, sprintName);
                sprintData.put(i + 1, sprintName);
            }

        if (dataValues == null || dataValues.isEmpty()) {
            log.info("No data");
        } else {
            for (List rows : dataValues) {
                String teamName= (String) rows.get(0);
                    for (int i =1 ;i<rows.size();i+=2){

                            if (!rows.get(i).equals("")){
                            String taken = (String) rows.get(i);
                            String completed = (String) rows.get(i+1);
                            log.info(" Team "+teamName+" taken "+taken+" completed "+completed+" sprint "+sprintData.get(i));

                            SprintData sprintData1 = new SprintData();
                            sprintData1.setName(teamName);
                            sprintData1.setTaken(taken);
                            sprintData1.setCompleted(completed);
                            sprintData1.setTeam(sprintData.get(i));


                            }
                    }


            }
        }


        }
        return null;
    }

    public static void main(String[] args) throws GeneralSecurityException, IOException {
        SprintDataLoader sprintDataLoader = new SprintDataLoader();
        sprintDataLoader.loadSprintData();
   }

}
