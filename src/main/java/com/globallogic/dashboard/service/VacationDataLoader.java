package com.globallogic.dashboard.service;

import com.globallogic.dashboard.model.Member;
import com.globallogic.dashboard.model.Vacation;
import com.globallogic.dashboard.repository.VacationRepository;
import com.globallogic.dashboard.service.util.PropertyUtil;
import com.globallogic.dashboard.to.MemberCreateDto;
import com.globallogic.dashboard.to.VacationCreateDto;
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
import org.apache.commons.lang3.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
//@Primary
@Component
public class VacationDataLoader implements DataLoader{

    private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
    private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String CREDENTIALS_FOLDER = "credentials";

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
    private static final String CLIENT_SECRET_DIR = "/client_secret.json";

    private static final Logger log = LoggerFactory.getLogger(VacationDataLoader.class);
    public static final String CONFIG_PROPERTIES = "config.properties";


    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        InputStream in = VacationDataLoader.class.getResourceAsStream(CLIENT_SECRET_DIR);
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
        return null;
    }
    @Override
    public List<VacationCreateDto> loadVacationData() {


        List<VacationCreateDto> vacationCreateDtos = new ArrayList<>();
        try {
            Properties properties = PropertyUtil.loadProperties(CONFIG_PROPERTIES);
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            final String spreadsheetId = "1kD1UO68ceFnMnVU0u_TBkLf32k9BdSAqLu-3_PawDxk";
            final String monthRange = "B1:ND1";
            final String daysRange = "B2:ND2";
            final String range = "B3:ND54";


            Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                    .setApplicationName(APPLICATION_NAME)
                    .build();
            ValueRange metadataResponse = service.spreadsheets().values()
                    .get(spreadsheetId, monthRange)
                    .execute();

            List<Object> days = service.spreadsheets().values()
                    .get(spreadsheetId, daysRange)
                    .execute().getValues().get(0);


            ValueRange response = service.spreadsheets().values()
                    .get(spreadsheetId, range)
                    .execute();

            Set<RangeMeta> rangeMetas = MonthUtil.extractMothRangesForObjects(metadataResponse.getValues().get(0));
            List<List<Object>> values = response.getValues();

            for (RangeMeta monthCell : rangeMetas) {

                log.info("Month:{}, range:{}-{}", monthCell.getMonthName(), monthCell.getRange().getMinimum(), monthCell.getRange().getMaximum());
            }


            if (values == null || values.isEmpty()) {
                log.info("No data found.");
            } else {
                for (List row : values) {
                    if (!row.isEmpty()) {
                        String startCell = null, endCell = null;
                        String name  = (String)row.get(0);
                        for (int i = 2; i < row.size(); i++) { //user
                            if (!row.isEmpty() && row.get(i).equals("1")) { //is a vacation
                                String monthForCellId = MonthUtil.getMonthForCellId(rangeMetas, i + 1);
                                String monthDay = (String) days.get(i);
                                if (startCell == null) {
                                    startCell = monthDay;
                                }
                                if (((i + 1) < row.size() && !row.get(i + 1).equals("1"))|| (i + 1) == row.size()) {
                                    endCell = monthDay;
                                    vacationCreateDtos.add(createDto(name, monthForCellId,startCell, endCell));
                                    startCell = null;
                                }//sets start / end
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new DataLoadingException("Errror while processing data.", e);
        }

        return vacationCreateDtos;

    }

    private VacationCreateDto createDto(String name, String month, String start, String end){
        Vacation v = new Vacation(name, month, start, end);
        return new VacationCreateDto(v);
    }


    public static class RangeMeta {
        private String monthName;
        private Range<Integer> range;

        public RangeMeta(String monthName, Range<Integer> range) {
            this.monthName = monthName;
            this.range = range;
        }

        public String getMonthName() {
            return monthName;
        }

        public Range<Integer> getRange() {
            return range;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RangeMeta rangeMeta = (RangeMeta) o;
            return Objects.equals(monthName, rangeMeta.monthName) &&
                    Objects.equals(range, rangeMeta.range);
        }

        @Override
        public int hashCode() {

            return Objects.hash(monthName, range);
        }
    }

}














