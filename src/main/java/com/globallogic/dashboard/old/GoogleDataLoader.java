package com.globallogic.dashboard.old;

import com.globallogic.dashboard.*;
import com.globallogic.dashboard.evaluator.MonthEndDataEvaluator;
import com.globallogic.dashboard.evaluator.MonthStartDataEvaluator;
import com.globallogic.dashboard.evaluator.VacationEndDataEvaluator;
import com.globallogic.dashboard.evaluator.VacationStartDataEvaluator;
import com.globallogic.dashboard.event.MonthEventListener;
import com.globallogic.dashboard.event.VacationEventListener;
import com.globallogic.dashboard.service.DataLoader;
import com.globallogic.dashboard.service.TeamService;
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
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class GoogleDataLoader implements DataLoader {
    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "src/main/resources/credentials";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CLIENT_SECRET_DIR = "/client_secret.json";

    private static final Logger log = LoggerFactory.getLogger(GoogleDataLoader.class);
    public static final String CONFIG_PROPERTIES = "config.properties";


    private TeamService teamService;

    public GoogleDataLoader(TeamService teamService) {
        this.teamService = teamService;
    }

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {

        InputStream in = GoogleDataLoader.class.getResourceAsStream(CLIENT_SECRET_DIR);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
                .setAccessType("offline")
                .build();
        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    @Override
    public List<VacationDto> loadVacationData() {
        final String monthRange = "D1:ND1"; //todo as Value in property files
        final String daysRange = "D2:ND2";
        final String vacationRange = "B3:ND56"; //todo next rows as well

        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            final String spreadsheetId = "1kD1UO68ceFnMnVU0u_TBkLf32k9BdSAqLu-3_PawDxk";

            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            ValueRange monthRow = service.spreadsheets().values()
                    .get(spreadsheetId, monthRange)
                    .execute();

            List<Object> days = service.spreadsheets().values()
                    .get(spreadsheetId, daysRange)
                    .execute().getValues().get(0);

            List<List<Object>> listVacations = service.spreadsheets().values()
                    .get(spreadsheetId, vacationRange)
                    .execute().getValues();


            Processor<String> monthProcessor = new Processor<>(new MonthStartDataEvaluator(), new MonthEndDataEvaluator());
            MonthEventListener monthEventListener = new MonthEventListener();
            monthProcessor.withEventListener(monthEventListener);
            List<String> stringRowata = DataLoaderUtil.toListOfStrings(monthRow.getValues().get(0));
            monthProcessor.process(stringRowata);
            List<MonthDto> monthDtos = monthEventListener.getMonthDtos();

            MonthUtil mu = new MonthUtil(monthDtos);
            mu.setDays(days);

            Processor<String> vacationProcessor = new Processor<>(new VacationStartDataEvaluator(), new VacationEndDataEvaluator());
            VacationEventListener vacationEventListener = new VacationEventListener(mu);
            vacationProcessor.withEventListener(vacationEventListener);
            for (List<Object> vacation : listVacations) {
                vacationProcessor.process(DataLoaderUtil.toListOfStrings(vacation));
            }
            return vacationEventListener.getVacationDtos();

        } catch (Exception e) {
            throw new RuntimeException("Error while processing data.", e);
        }
    }

    public void loadData() {
    }


}