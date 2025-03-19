package org.cassandra.monitor.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app-cassandra")
public class CassandraProperties {

    private String contactPoints;
    private String keyspaceName;
    private String localDatacenter;
    private String schemaAction;

    public String getContactPoints() {
        return contactPoints;
    }

    public void setContactPoints(String contactPoints) {
        this.contactPoints = contactPoints;
    }

    public String getKeyspaceName() {
        return keyspaceName;
    }

    public void setKeyspaceName(String keyspaceName) {
        this.keyspaceName = keyspaceName;
    }

    public String getLocalDatacenter() {
        return localDatacenter;
    }

    public void setLocalDatacenter(String localDatacenter) {
        this.localDatacenter = localDatacenter;
    }

    public String getSchemaAction() {
        return schemaAction;
    }

    public void setSchemaAction(String schemaAction) {
        this.schemaAction = schemaAction;
    }
}
