package nl.bsoft.batch.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Report {
    private static final Logger LOGGER = LoggerFactory.getLogger(Report.class);

    private String Date;
    private String Impressions;
    private String Clicks;
    private String Earning;

    public Report() {
    }

    public Report(String date, String impressions, String clicks, String earning) {
        this.Date = date;
        this.Impressions = impressions;
        this.Clicks = clicks;
        this.Earning = earning;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getImpressions() {
        return Impressions;
    }

    public void setImpressions(String impressions) {
        Impressions = impressions;
    }

    public String getClicks() {
        return Clicks;
    }

    public void setClicks(String clicks) {
        Clicks = clicks;
    }

    public String getEarning() {
        return Earning;
    }

    public void setEarning(String earning) {
        Earning = earning;
    }

    public String toString() {
        String result = "Report: " + this.Date + ", " + this.Impressions + ", " + this.Clicks + ", " + this.Earning;
        return result;
    }

}