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
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.PlatformTransactionManager;

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
            serviceh2.delete(result);
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
        report.setHash(Integer.toHexString(report.hashCode()));

        result = servicepg.save(report);
        Assert.assertNotNull(result);

        servicepg.delete(result);
        logger.info("End   test: {}", name.getMethodName());
    }

}
