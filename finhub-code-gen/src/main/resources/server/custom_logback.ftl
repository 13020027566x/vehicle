<included>
    <!-- 标识是否有自定义 logger -->
    <property name="CUSTOM_LOGGER" value="true"/>

    <logger name="org.springframework.kafka" level="ERROR" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="net.sf.ehcache" level="ERROR" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="org.springframework.security" level="ERROR" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="shaded.org.apache" level="WARN" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="org.apache" level="WARN" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="org.apache.velocity" level="WARN" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="org.apache.myfaces" level="WARN" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="org.dbunitorg.springframework" level="WARN" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="org.springframework" level="WARN" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>
    <logger name="org.hibernate" level="WARN" additivity="false">
        <appender-ref ref="${r'${'}EFFECTIVE_LOG_APPENDER}"/>
    </logger>

</included>
