package nl.bsoft.batch.service.pg;

import nl.bsoft.batch.model.h2.ReportH2;
import nl.bsoft.batch.model.pg.ReportPg;
import nl.bsoft.batch.repo.pg.ReportPgRepo;
import nl.bsoft.batch.service.h2.ServiceH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicePg {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServicePg.class);

    @Autowired
    ReportPgRepo repository;

    public ReportPg save(final ReportPg report) {
        ReportPg result = null;

        result = repository.save(report);

        return result;
    }

    public void delete(final ReportPg report) {
        repository.delete(report);
    }
}
