package nl.bsoft.batch;

import nl.bsoft.batch.model.h2.ReportH2;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H2DbTests extends AbstractTest {
    private static final Logger logger = LoggerFactory.getLogger(H2DbTests.class);

    @Test
    public void storeReportH2() {
        logger.info("Start test: {}", name.getMethodName());
        ReportH2 report = new ReportH2();
        ReportH2 result = null;

        report.setDate("25-01-2019");
        report.setImpressions("Goede keuze");
        report.setClicks("vijf");
        report.setEarning("Verdienste minimaal");

        try {
            result = serviceh2.save(report);
        } catch (Exception e) {
            logger.error("Error saving report: {}, message: {}", report.toString(), e);
        }
        Assert.assertNotNull(result);

        try {
            serviceh2.deleteAll();
        } catch (Exception e) {
            logger.error("Error deleting report: {}, message: {}", report.toString(), e);
        }
        logger.info("End   test: {}", name.getMethodName());
    }

    @Test
    public void readReportH2() {
        logger.info("Start test: {}", name.getMethodName());
        ReportH2 report = new ReportH2();
        ReportH2 result01 = null;

        report.setDate("26-01-2019");
        report.setImpressions("Zeer Goede keuze");
        report.setClicks("zes");
        report.setEarning("Verdienste maximaal");

        try {
            result01 = serviceh2.save(report);
        } catch (Exception e) {
            logger.error("Error saving report: {}, message: {}", report.toString(), e);
        }
        Assert.assertNotNull(result01);


        Iterable<ReportH2> results = null;

        try {
            results = serviceh2.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving reports, message: {}", e);
        }

        Assert.assertEquals(1, size(results));

        for (ReportH2 result : results) {
            logger.info("Found report: {}", result.toString());
        }

        try {
            serviceh2.deleteAll();
        } catch (Exception e) {
            logger.error("Error deleting report: {}, message: {}", report.toString(), e);
        }
        logger.info("End   test: {}", name.getMethodName());
    }
}
