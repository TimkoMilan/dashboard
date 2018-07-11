package com.globallogic.dashboard.service;

import com.globallogic.dashboard.DashboardApplication;
import com.globallogic.dashboard.service.util.PropertyUtil;
import com.globallogic.dashboard.to.*;
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
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Component
public class GoogleDataLoader implements DataLoader {
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "credentials";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CLIENT_SECRET_DIR = "/client_secret.json";

    private static final Logger log = LoggerFactory.getLogger(GoogleDataLoader.class);
    public static final String CONFIG_PROPERTIES = "config.properties";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        InputStream in = DashboardApplication.class.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }


    @Override
    public List<MemberCreateDto> loadData() {
        Properties prop = null;
        try {
             prop = PropertyUtil.loadProperties(CONFIG_PROPERTIES);
        } catch (IOException e) {
            throw new DataLoadingException("Error while loading properties", e);
        }


        //TODO properties row from datasheet

        String position = prop.getProperty("position");
        String name = prop.getProperty("name");
        String billingValue = prop.getProperty("billingValue");
        String focus = prop.getProperty("focus");
        String sheetId = prop.getProperty("spreadsheetId");
        String idProject = prop.getProperty("idProject");
        String projectName = prop.getProperty("projectName");
        String projectFocus = prop.getProperty("projectFocus");
        String start = prop.getProperty("start");
        String end = prop.getProperty("end");
        String sprintName = prop.getProperty("sprintName");
        String storyPointTakken = prop.getProperty("storyPointTakken");
        String storyPointClossed = prop.getProperty("storyPointClossed");
        String members = prop.getProperty("members");
        String sprint = prop.getProperty("sprint");
        String username = prop.getProperty("username");

        log.trace("focus {}", username);

        List<MemberCreateDto> memberCreateDtos = new ArrayList<>();
        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            final String spreadsheetId = sheetId;
            final String range = "Class Data!A2:E";
            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();
            List<List<Object>> values = response.getValues();
            System.out.println("work it ");
            if (values == null || values.isEmpty()) {
                System.out.println("No data found.");
            } else {
                for (List row : values) {
                    MemberCreateDto memberCreateDto = new MemberCreateDto();

                    //TODO Member info
                    memberCreateDto.setName((String) row.get(Integer.parseInt(name)));
                    memberCreateDto.setPosition((String) row.get(Integer.parseInt(position)));
                    memberCreateDto.setBillingValue((String) row.get(Integer.parseInt(billingValue)));
                    memberCreateDto.setFocus((String) row.get(Integer.parseInt(focus)));

                    memberCreateDtos.add(memberCreateDto);
                }
            }
        } catch (Exception e) {
            throw new DataLoadingException("Errror while processing data.", e);
        }

        return memberCreateDtos;
    }

    @Override
    public List<VacationCreateDto> loadVacationData() {
        return null;
    }
}


