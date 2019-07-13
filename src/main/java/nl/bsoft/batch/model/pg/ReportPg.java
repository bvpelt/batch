package nl.bsoft.batch.model.pg;

import nl.bsoft.batch.util.CryptoHash;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.util.Objects;

@Entity(name = "ReportPg")
@Table(name = "REPORTPG")
public class ReportPg {
    private static final Logger logger = LoggerFactory.getLogger(ReportPg.class);

    @javax.persistence.Id
    @Column(name = "id")
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

    @Column(name = "HASH")
    private String Hash;

    public ReportPg() {
    }

    public ReportPg(Long id, String date, String impressions, String clicks, String earning, String hash) {
        this.Id = id;
        this.Date = date;
        this.Impressions = impressions;
        this.Clicks = clicks;
        this.Earning = earning;
        this.Hash = hash;
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

    public String getHash() {
        return Hash;
    }

    public void setHash(String hash) {
        Hash = hash;
    }

    @Override
    public String toString() {
        String result = "ReportOut: " + this.Id + ", " + functionalString() + ", " + this.Hash;
        return result;
    }

    private String functionalString() {
        String result = this.Date + ", " + this.Impressions + ", " + this.Clicks + ", " + this.Earning;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportPg that = (ReportPg) o;
        return Id == that.Id &&
                Objects.equals(Date, that.Date) &&
                Objects.equals(Impressions, that.Impressions) &&
                Objects.equals(Clicks, that.Clicks) &&
                Objects.equals(Earning, that.Earning) &&
                Objects.equals(Hash, that.Hash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Date, Impressions, Clicks, Earning);
    }

    public String calcHash() {
        String result = null;

        CryptoHash ch = new CryptoHash();
        try {
            result = ch.getHash(functionalString());
        } catch (Exception e) {
            logger.error("Problem calculating hash: {}", e);
        }
        return result;
    }
}