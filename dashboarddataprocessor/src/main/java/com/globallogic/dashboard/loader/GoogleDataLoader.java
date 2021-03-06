package com.globallogic.dashboard.loader;

import com.globallogic.dashboard.evaluator.MonthEndDataEvaluator;
import com.globallogic.dashboard.evaluator.MonthStartDataEvaluator;
import com.globallogic.dashboard.evaluator.VacationEndDataEvaluator;
import com.globallogic.dashboard.evaluator.VacationStartDataEvaluator;
import com.globallogic.dashboard.event.*;
import com.globallogic.dashboard.processor.Processor;
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
import java.util.*;


public class GoogleDataLoader implements DataLoader {

    private GoogleDataLoaderConfig googleDataLoaderConfig;

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "src/main/resources/credentials";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CLIENT_SECRET_DIR = "/client_secret.json";

    private static final Logger log = LoggerFactory.getLogger(GoogleDataLoader.class);
    public static final String CONFIG_PROPERTIES = "config.properties";

    public GoogleDataLoader(GoogleDataLoaderConfig googleDataLoaderConfig) {
        this.googleDataLoaderConfig = googleDataLoaderConfig;
    }

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


    @Override
    public List<VacationData> loadVacationData() {
        String spreadSheetIdconfig = googleDataLoaderConfig.getSpreadsheetId();
        Map<String, String> offsetConfig = googleDataLoaderConfig.getOffset();
        Map<String, String> vacationRangesConfig = googleDataLoaderConfig.getVacationRanges();
        List<VacationData> vacationData = new ArrayList<>();
        vacationRangesConfig.forEach((year,range) ->
                vacationData.addAll(loadVacationDataByYear(year,range, spreadSheetIdconfig,
                        Integer.parseInt(offsetConfig.get(year))))
        );
        return vacationData;
    }

    private List<VacationData> loadVacationDataByYear(String year, String range, final String spreadSheetIdconfig,
                                                      final Integer offset){

        List<String> rangesSplit = getRangesSplitByComma(range);
        String monthRangeconfig = rangesSplit.get(0);
        String dayRangeconfig = rangesSplit.get(1);
        String vacationRangeconfig = rangesSplit.get(2);

        final String monthRange = monthRangeconfig;
        final String daysRange =  dayRangeconfig;
        final String vacationRange =vacationRangeconfig;

        try {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            final String spreadsheetId = spreadSheetIdconfig;

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
            List<MonthData> monthData = monthEventListener.getMonthData();

            MonthUtil mu = new MonthUtil(monthData);
            mu.setDays(days);
            mu.setMonthYear(Integer.parseInt(year));

            Integer positionIndex = Integer.parseInt(googleDataLoaderConfig.getPositionCell().get(year));


            Processor<String> vacationProcessor = new Processor<>(new VacationStartDataEvaluator(), new VacationEndDataEvaluator());
            VacationEventListener vacationEventListener = new VacationEventListener(mu, offset, positionIndex);
            vacationProcessor.withEventListener(vacationEventListener);
            for (List<Object> vacation : listVacations) {
                vacationProcessor.process(DataLoaderUtil.toListOfStrings(vacation));
            }
            return vacationEventListener.getVacationData();

        } catch (Exception e) {
            throw new DataLoadingException("Error while processing data.", e);
        }
    }


    public void loadData() {
        throw new UnsupportedOperationException("load data is not implemented yet.");
    }

    private List<String> getRangesSplitByComma(String rangesString){
        rangesString.replaceAll("\\s+",""); //remove whitespaces
        return Arrays.asList(rangesString.split(","));
    }


}
