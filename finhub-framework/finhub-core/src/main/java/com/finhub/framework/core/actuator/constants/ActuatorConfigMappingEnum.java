package com.finhub.framework.core.actuator.constants;

/**
 * @author : liuwei
 * @date : 2021/12/21
 * @desc :
 */
public enum ActuatorConfigMappingEnum {
    /**
     *
     */
    CASSANDRA("cassandra", "management.health.cassandra.enabled"),
    /**
     *
     */
    COUCHBASE("couchbase", "management.health.couchbase.enabled"),
    /**
     *
     */
    DB("db", "management.health.db.enabled"),
    /**
     *
     */
    DEFAULTS("defaults", "management.health.defaults.enabled"),
    /**
     *
     */
    DISKSPACE("diskspace", "management.health.diskspace.enabled"),
    /**
     *
     */
    ELASTICSEARCH("elasticsearch", "management.health.elasticsearch.enabled"),
    /**
     *
     */
    INFLUXDB("influxdb", "management.health.influxdb.enabled"),
    /**
     *
     */
    JMS("jms", "management.health.jms.enabled"),
    /**
     *
     */
    LDAP("ldap", "management.health.ldap.enabled"),
    /**
     *
     */
    LIVENESSSTATE("livenessstate", "management.health.livenessstate.enabled"),
    /**
     *
     */
    MAIL("mail", "management.health.mail.enabled"),
    /**
     *
     */
    MONGO("mongo", "management.health.mongo.enabled"),
    /**
     *
     */
    NEO4J("neo4j", "management.health.neo4j.enabled"),
    /**
     *
     */
    PING("ping", "management.health.ping.enabled"),
    /**
     *
     */
    RABBIT("rabbit", "management.health.rabbit.enabled"),
    /**
     *
     */
    READINESSSTATE("readinessstate", "management.health.readinessstate.enabled"),
    /**
     *
     */
    REDIS("redis", "management.health.redis.enabled"),
    /**
     *
     */
    SOLR("solr", "management.health.solr.enabled"),
    /**
     *
     */
    PROBES("probes", "management.health.probes.enabled"),
    ;
    ActuatorConfigMappingEnum(String property, String metaName) {
        this.property = property;
        this.metaName = metaName;
    }

    private String property;

    private String metaName;

    private boolean enabled = false;


    public String getProperty() {
        return property;
    }

    public String getMetaName() {
        return metaName;
    }

    public boolean isEnabled() {
        return enabled;
    }

}
