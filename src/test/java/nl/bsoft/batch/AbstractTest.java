package nl.bsoft.batch;

import nl.bsoft.batch.service.h2.ServiceH2;
import nl.bsoft.batch.service.pg.ServicePg;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTest {

    @Rule
    public TestName name = new TestName();

    @Autowired
    ServiceH2 serviceh2;

    @Autowired
    ServicePg servicepg;

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
