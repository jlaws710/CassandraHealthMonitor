package org.cassandra.monitor.config;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.shaded.guava.common.collect.ImmutableList;
import org.cassandra.monitor.model.CassandraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.InetSocketAddress;
import java.util.List;

@Configuration
@EnableConfigurationProperties(CassandraProperties.class)
public class CassandraBeans {

    @Bean
    List<CassandraProperties> cassandraConfig() {
        return ImmutableList.of();
    }

    @Bean
    CqlSession cqlSession(Environment env) {

        return CqlSession.builder()
            .addContactPoint(new InetSocketAddress(env.getRequiredProperty("app-cassandra.contactPoints"), 9042))
            .withKeyspace(env.getRequiredProperty("app-cassandra.keyspaceName"))
            .withLocalDatacenter(env.getRequiredProperty("app-cassandra.localDatacenter"))
            .build();
    }
}
