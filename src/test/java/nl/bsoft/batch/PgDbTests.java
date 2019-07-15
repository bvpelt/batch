package nl.bsoft.batch;

import nl.bsoft.batch.model.pg.ReportPg;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PgDbTests extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(PgDbTests.class);

    @Test
    public void storeReportPg() {
        logger.info("Start test: {}", name.getMethodName());
        ReportPg report = new ReportPg();
        ReportPg result = null;

        report.setDate("25-01-2019");
        report.setImpressions("Goede keuze");
        report.setClicks("vijf");
        report.setEarning("Verdienste minimaal");
        report.setHash(report.calcHash());

        try {
            result = servicepg.save(report);
        } catch (Exception e) {
            logger.error("Error saving report: {}, message: {}", report.toString(), e);
        }
        Assert.assertNotNull(result);

        try {
            servicepg.deleteAll();
        } catch (Exception e) {
            logger.error("Error deleting report: {}, message: {}", report.toString(), e);
        }
        logger.info("End   test: {}", name.getMethodName());
    }

    @Test
    public void readReportPg() {
        logger.info("Start test: {}", name.getMethodName());
        ReportPg report = new ReportPg();
        ReportPg result01 = null;

        report.setDate("26-01-2019");
        report.setImpressions("Zeer Goede keuze");
        report.setClicks("zes");
        report.setEarning("Verdienste maximaal");
        report.setHash(report.calcHash());

        try {
            result01 = servicepg.save(report);
            Assert.assertNotNull(result01);
            ReportPg report01 = new ReportPg();
            report01.setDate("27-01-2019");
            report01.setImpressions("Erg slecht");
            report01.setClicks("zeven");
            report01.setEarning("Goedkoop");
            result01 = servicepg.save(report01);
        } catch (Exception e) {
            logger.error("Error saving report: {}, message: {}", report.toString(), e);
        }
        Assert.assertNotNull(result01);


        Iterable<ReportPg> results = null;

        try {
            results = servicepg.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving reports, message: {}", e);
        }

        Assert.assertEquals(2, size(results));

        for (ReportPg result : results) {
            logger.info("Found report: {}", result.toString());
        }
        try {
            servicepg.deleteAll();
        } catch (Exception e) {
            logger.error("Error deleting report: {}, message: {}", report.toString(), e);
        }
        logger.info("End   test: {}", name.getMethodName());
    }

}
