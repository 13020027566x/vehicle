package com.finhub.framework.mybatis.config;

import com.finhub.framework.core.str.StrConstants;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * Mapper Ignore Utils
 *
 * @author TaoBangren
 * @version 1.0.0
 * @since 2021/09/08 14:11
 */
@Slf4j
@UtilityClass
public class ConfigUtils {

    private static final String IGNORE_MAPPER_RESOURCE_LOCATION = "config.properties";

    private static final String IGNORE_MAPPER_KEY = "ignore_mapper_ids";

    private static final String AUTO_MAPPER_KEY = "auto_key_mappers";

    private static final String NOT_HAS_ID_KEY = "not_has_id_mappers";

    private static final String LOGIC_DELETE_FIELD_KEY = "logic_delete_fields";

    private static final Set<String> IGNORE_MAPPER_IDS = Sets.newHashSet();

    private static final Set<String> AUTO_KEY_MAPPERS = Sets.newHashSet();

    private static final Set<String> NOT_HAS_ID_MAPPERS = Sets.newHashSet();

    private static final Map<String, String> LOGIC_DELETE_FIELD_MAPPERS = Maps.newHashMap();

    static {
        Long time = System.currentTimeMillis();
        log.info("ConfigUtils init start");
        try {
            Properties properties = PropertiesLoaderUtils.loadAllProperties(IGNORE_MAPPER_RESOURCE_LOCATION);
            String ignoreMapperIds = properties.getProperty(IGNORE_MAPPER_KEY);
            if (StrUtil.isNotBlank(ignoreMapperIds)) {
                IGNORE_MAPPER_IDS.addAll(Arrays.asList(ignoreMapperIds.split(StrConstants.S_COMMA)));
            }

            String autoKeyMappers = properties.getProperty(AUTO_MAPPER_KEY);
            if (StrUtil.isNotBlank(autoKeyMappers)) {
                AUTO_KEY_MAPPERS.addAll(Arrays.asList(autoKeyMappers.split(StrConstants.S_COMMA)));
            }

            String notHasIdMappers = properties.getProperty(NOT_HAS_ID_KEY);
            if (StrUtil.isNotBlank(notHasIdMappers)) {
                NOT_HAS_ID_MAPPERS.addAll(Arrays.asList(notHasIdMappers.split(StrConstants.S_COMMA)));
            }

            String logicDeleteFieldMapper = properties.getProperty(LOGIC_DELETE_FIELD_KEY);
            if (StrUtil.isNotBlank(logicDeleteFieldMapper)) {
                String[] logicDeleteFieldMappers = logicDeleteFieldMapper.split(StrConstants.S_COMMA);
                for (String mapper : logicDeleteFieldMappers) {
                    String[] fieldMappers = mapper.split(StrConstants.S_COLON);
                    if (fieldMappers.length == 2) {
                        LOGIC_DELETE_FIELD_MAPPERS.put(fieldMappers[0], fieldMappers[1]);
                    }
                }
            }
        } catch (Exception e) {
            log.error("ConfigUtils init fail.", e);
        }
        log.info("ConfigUtils init end, cost time:{}", System.currentTimeMillis() - time);
    }

    public static boolean isIgnore(final String id) {
        return IGNORE_MAPPER_IDS.contains(id);
    }

    public static boolean isAutoKey(final String mapper) {
        return AUTO_KEY_MAPPERS.contains(mapper);
    }

    public static boolean notHasId(final String mapper) {
        return NOT_HAS_ID_MAPPERS.contains(mapper);
    }

    public static String getLogicDeleteFieldName(final String mapper) {
        return LOGIC_DELETE_FIELD_MAPPERS.get(mapper);
    }
}
