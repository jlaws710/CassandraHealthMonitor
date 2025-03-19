package org.cassandra.monitor.service;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.servererrors.QueryConsistencyException;
import org.cassandra.monitor.model.CassandraProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
public class CassandraHealthCheck {

    @Autowired
    private CqlSession session;
    private static final Logger logger = LoggerFactory.getLogger(CassandraHealthCheck.class);

    private CassandraProperties cassandraProperties;

    public CassandraHealthCheck(CassandraProperties cassandraProperties) {
        this.cassandraProperties = cassandraProperties;
    }

    @Scheduled(fixedRate = 15000)
    public void checkCassandraHealth() {
        try {
            session.execute("SELECT release_version FROM system.local");
            logger.info("Cassandra is healthy at " + cassandraProperties.getContactPoints());
        } catch (QueryConsistencyException e) {
            logger.error("Cassandra query error: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Cassandra is down or unreachable");
        }
    }
}
