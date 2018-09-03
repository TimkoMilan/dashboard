package com.globallogic.dashboard.loader;

import java.util.Map;

public class GoogleDataLoaderConfig {

    private String spreadsheetId;
    private Map<String, String> vacationRanges;
    private Map<String, String> offset;

    public String getSpreadsheetId() {
        return spreadsheetId;
    }

    public void setSpreadsheetId(String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
    }

    public Map<String, String> getVacationRanges() {
        return vacationRanges;
    }

    public void setVacationRanges(Map<String, String> vacationRanges) {
        this.vacationRanges = vacationRanges;
    }

    public Map<String, String> getOffset() {
        return offset;
    }

    public void setOffset(Map<String, String> offset) {
        this.offset = offset;
    }
}
