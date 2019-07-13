package nl.bsoft.batch;

import nl.bsoft.batch.model.h2.ReportH2;
import nl.bsoft.batch.model.pg.ReportPg;
import nl.bsoft.batch.service.h2.ServiceH2;
import nl.bsoft.batch.service.pg.ServicePg;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BatchApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(BatchApplicationTests.class);

    @Rule
    public TestName name = new TestName();

    @Autowired
    ServiceH2 serviceh2;

    @Autowired
    ServicePg servicepg;

    @Test
    public void contextLoads() {
        logger.info("Start test: {}", name.getMethodName());
        logger.info("End   test: {}", name.getMethodName());
    }

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

    public int size(final Iterable data) {

        if (data instanceof Collection) {
            return ((Collection<?>) data).size();
        }

        int counter = 0;
        for (Object i : data) {
            counter++;
        }
        return counter;
    }
}
