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
public class BatchApplicationTests extends AbstractTest  {
    private static final Logger logger = LoggerFactory.getLogger(BatchApplicationTests.class);


    @Test
    public void contextLoads() {
        logger.info("Start test: {}", name.getMethodName());
        logger.info("End   test: {}", name.getMethodName());
    }
}
