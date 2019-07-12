package nl.bsoft.batch.model.h2;

import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "ReportH2")
@Table(name = "REPORTH2")
public class ReportH2 {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReportH2.class);

    @javax.persistence.Id
    @Column(name = "ID")
    @GenericGenerator(name = "generator", strategy = "sequence-identity")
    @GeneratedValue(generator = "generator")
    private long Id;

    @Column(name = "DATE")
    private String Date;

    @Column(name = "IMPRESSIONS")
    private String Impressions;

    @Column(name = "CLICKS")
    private String Clicks;

    @Column(name = "EARNINGS")
    private String Earning;

    public ReportH2() {
    }

    public ReportH2(Long id, String date, String impressions, String clicks, String earning) {
        this.Id = id;
        this.Date = date;
        this.Impressions = impressions;
        this.Clicks = clicks;
        this.Earning = earning;
    }

    public Long getId() {
        return Id;
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

    @Override
    public String toString() {
        String result = "ReportIn: " + this.Id + ", " + this.Date + ", " + this.Impressions + ", " + this.Clicks + ", " + this.Earning;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportH2 that = (ReportH2) o;
        return Id == that.Id &&
                Objects.equals(Date, that.Date) &&
                Objects.equals(Impressions, that.Impressions) &&
                Objects.equals(Clicks, that.Clicks) &&
                Objects.equals(Earning, that.Earning);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Date, Impressions, Clicks, Earning);
    }
}

