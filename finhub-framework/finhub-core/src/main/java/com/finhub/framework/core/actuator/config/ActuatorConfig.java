package com.finhub.framework.core.actuator.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author : liuwei
 * @date : 2021/12/21
 * @desc :
 */
@Data
public class ActuatorConfig {

    /**
     * Whether to enable cassandra health check
     */
    @Value("${management.health.cassandra.enabled}")
    private boolean cassandra;

    /**
     * Whether to enable couchbase health check
     */
    @Value("${management.health.couchbase.enabled}")
    private boolean couchbase;

    /**
     * Whether to enable database health check
     */
    @Value("${management.health.db.enabled}")
    private boolean db;

    /**
     * Whether to enable default health indicators
     */
    @Value("${management.health.defaults.enabled}")
    private boolean defaults;

    /**
     * Whether to enable disk space health check
     */
    @Value("${management.health.diskspace.enabled}")
    private boolean diskspace;

    /**
     * Whether to enable elasticsearch health check
     */
    @Value("${management.health.elasticsearch.enabled}")
    private boolean elasticsearch;

    /**
     * Whether to enable influxdb health check
     */
    @Value("${management.health.influxdb.enabled}")
    private boolean influxdb;

    /**
     * Whether to enable jms health check
     */
    @Value("${management.health.jms.enabled}")
    private boolean jms;

    /**
     * Whether to enable ldap health check
     */
    @Value("${management.health.ldap.enabled}")
    private boolean ldap;

    /**
     * Whether to enable liveness health check
     */
    @Value("${management.health.livenessstate.enabled}")
    private boolean livenessstate;

    /**
     * Whether to enable mail health check
     */
    @Value("${management.health.mail.enabled}")
    private boolean mail;

    /**
     * Whether to enable mongo health check
     */
    @Value("${management.health.mongo.enabled}")
    private boolean mongo;

    /**
     * Whether to enable neo4j health check
     */
    @Value("${management.health.neo4j.enabled}")
    private boolean neo4j;

    /**
     * Whether to enable ping health check
     */
    @Value("${management.health.ping.enabled}")
    private boolean ping;

    /**
     * Whether to enable rabbit health check
     */
    @Value("${management.health.rabbit.enabled}")
    private boolean rabbit;

    /**
     * Whether to enable readinessstate health check
     */
    @Value("${management.health.readinessstate.enabled}")
    private boolean readinessstate;

    /**
     * Whether to enable redis health check
     */
    @Value("${management.health.redis.enabled}")
    private boolean redis;

    /**
     * Whether to enable solr health check
     */
    @Value("${management.health.solr.enabled}")
    private boolean solr;

    /**
     * Whether to enable liveness and readiness probes.
     */
    @Value("${management.health.probes.enabled}")
    private boolean probes;

}
