package nl.bsoft.batch.service.h2;

import nl.bsoft.batch.config.H2Config;
import nl.bsoft.batch.exceptions.ReportH2TransActionException;
import nl.bsoft.batch.model.h2.ReportH2;
import nl.bsoft.batch.repo.h2.ReportH2Repo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServiceH2 {
    private static final Logger logger = LoggerFactory.getLogger(ServiceH2.class);

    @Autowired
    ReportH2Repo repository;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = ReportH2TransActionException.class)
    public ReportH2 save(final ReportH2 report) throws ReportH2TransActionException {
        ReportH2 result = null;

        try {
            result = repository.save(report);
        } catch (Exception e) {
            logger.error("Error saving report: {}, exception: {}", report.toString(), e);
            throw new ReportH2TransActionException(e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = ReportH2TransActionException.class)
    public void delete(final ReportH2 report) throws ReportH2TransActionException {
        try {
            repository.delete(report);
        } catch (Exception e) {
            logger.error("Error deleting report: {}, exception: {}", report.toString(), e);
            throw new ReportH2TransActionException(e.getMessage());
        }
    }
}
