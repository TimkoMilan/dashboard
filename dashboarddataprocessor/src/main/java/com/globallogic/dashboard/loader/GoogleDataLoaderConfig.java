package com.globallogic.dashboard.loader;

public class GoogleDataLoaderConfig {
    private String monthRange;
    private String daysRange;
    private String vacationRange;
    private String spreadsheetId;

    public String getSpreadsheetId() {
        return spreadsheetId;
    }

    public void setSpreadsheetId(String spreadsheetId) {
        this.spreadsheetId = spreadsheetId;
    }

    public String getMonthRange() {
        return monthRange;
    }

    public void setMonthRange(String monthRange) {
        this.monthRange = monthRange;
    }

    public String getDaysRange() {
        return daysRange;
    }

    public void setDaysRange(String daysRange) {
        this.daysRange = daysRange;
    }

    public String getVacationRange() {
        return vacationRange;
    }

    public void setVacationRange(String vacationRange) {
        this.vacationRange = vacationRange;
    }


}
