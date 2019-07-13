package nl.bsoft.batch.service.pg;

import nl.bsoft.batch.exceptions.ReportPgTransActionException;
import nl.bsoft.batch.model.pg.ReportPg;
import nl.bsoft.batch.repo.pg.ReportPgRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ServicePg {
    private static final Logger logger = LoggerFactory.getLogger(ServicePg.class);

    @Autowired
    ReportPgRepo repository;

    @Transactional(propagation = Propagation.NESTED, rollbackFor = ReportPgTransActionException.class)
    public ReportPg save(final ReportPg report) throws ReportPgTransActionException {
        ReportPg result = null;

        try {
            result = repository.save(report);
        } catch (Exception e) {
            logger.error("Error saving report: {}, exception: {}", report.toString(), e);
            throw new ReportPgTransActionException(e.getMessage());
        }
        return result;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = ReportPgTransActionException.class)
    public Iterable<ReportPg> findAll() throws ReportPgTransActionException {
        Iterable<ReportPg> results = null;

        try {
            results = repository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving reports, exception: {}", e);
            throw new ReportPgTransActionException(e.getMessage());
        }
        return results;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = ReportPgTransActionException.class)
    public void delete(final ReportPg report) throws ReportPgTransActionException {
        try {
            repository.delete(report);
        } catch (Exception e) {
            logger.error("Error deleting report: {}, exception: {}", report.toString(), e);
            throw new ReportPgTransActionException(e.getMessage());
        }
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = ReportPgTransActionException.class)
    public void deleteAll() throws ReportPgTransActionException {
        try {
            repository.deleteAll();
        } catch (Exception e) {
            logger.error("Error deleting reports, exception: {}", e);
            throw new ReportPgTransActionException(e.getMessage());
        }
    }
}
