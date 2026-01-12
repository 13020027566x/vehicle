package com.finhub.framework.mybatis.injector;

import com.finhub.framework.core.Func;
import com.finhub.framework.mybatis.config.ConfigUtils;
import com.finhub.framework.mybatis.injector.method.DeleteBatchIdsPhysically;
import com.finhub.framework.mybatis.injector.method.DeleteByIdPhysically;
import com.finhub.framework.mybatis.injector.method.DeleteByMapPhysically;
import com.finhub.framework.mybatis.injector.method.DeletePhysically;
import com.finhub.framework.mybatis.po.BasePO;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.DefaultSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.*;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.ResultMap;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 自定义 SQL 注入器
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
public class CustomSqlInjector extends DefaultSqlInjector {

    private static final String RESULT_MAP_PRO = "resultMap";

    private static final String ID_TYPE_PRO = "idType";

    private static final String RESULT_MAP_STR = "ResultMap";

    private static final String PO_STR = "PO";

    private static final String KEY_COLUMN_PRO = "keyColumn";

    private static final String KEY_PROPERTY_PRO = "keyProperty";

    private static final String WITH_LOGIC_DELETE_PRO = "withLogicDelete";

    private static final String LOGIC_DELETE_FIELD_INFO_PRO = "logicDeleteFieldInfo";

    private static final String FIELD_LIST_PRO = "fieldList";

    @Override
    public void inspectInject(final MapperBuilderAssistant builderAssistant, final Class<?> mapperClass) {
        Class<?> modelClass = extractModelClass(mapperClass);
        if (modelClass != null) {
            String className = mapperClass.toString();
            Set<String> mapperRegistryCache = GlobalConfigUtils.getMapperRegistryCache(builderAssistant.getConfiguration());
            if (!mapperRegistryCache.contains(className)) {
                List<AbstractMethod> methodList = super.getMethodList(mapperClass);
                if (CollectionUtils.isNotEmpty(methodList)) {
                    TableInfo tableInfo = TableInfoHelper.initTableInfo(builderAssistant, modelClass);
                    // 自定义预处理 TableInfo
                    prepareTableInfo(tableInfo);
                    // 循环注入自定义方法
                    methodList.forEach(m -> m.inject(builderAssistant, mapperClass, modelClass, tableInfo));
                } else {
                    log.debug(mapperClass.toString() + ", No effective injection method was found.");
                }
                mapperRegistryCache.add(className);
            }
        }
    }

    /**
     * 预处理 TableInfo
     *
     * @param tableInfo
     */
    private TableInfo prepareTableInfo(final TableInfo tableInfo) {
        if (ConfigUtils.isAutoKey(tableInfo.getCurrentNamespace())) {
            BeanUtil.setProperty(tableInfo, ID_TYPE_PRO, IdType.AUTO);
        }

        if (Func.isEmpty(tableInfo.getResultMap()) && tableInfo.getEntityType().getSimpleName().endsWith(PO_STR)) {
            String resultMapName = StrUtil.replace(tableInfo.getEntityType().getSimpleName(), PO_STR, RESULT_MAP_STR);
            BeanUtil.setProperty(tableInfo, RESULT_MAP_PRO, resultMapName);
        }

        if (Func.isNotEmpty(tableInfo.getResultMap()) && tableInfo.getConfiguration().hasResultMap(tableInfo.getResultMap())) {
            ResultMap resultMap = tableInfo.getConfiguration().getResultMap(tableInfo.getResultMap());

            if (!resultMap.getMappedProperties().contains(BasePO.PROPERTY_ID)
                    && Objects.equals(BasePO.PROPERTY_ID, tableInfo.getKeyProperty())) {
                //数据库表列不包含id，并且当前tableInfo的主键属性为id时，清空主键信息
                BeanUtil.setProperty(tableInfo, KEY_COLUMN_PRO, null);
                BeanUtil.setProperty(tableInfo, KEY_PROPERTY_PRO, null);
            }

            TableFieldInfo logicDeleteFieldInfo = tableInfo.getLogicDeleteFieldInfo();
            String logicDeleteProperty = logicDeleteFieldInfo == null ? null : logicDeleteFieldInfo.getProperty();

            if (!resultMap.getMappedProperties().contains(BasePO.PROPERTY_IS_DEL)
                    && Objects.equals(BasePO.PROPERTY_IS_DEL, logicDeleteProperty)) {
                //数据库表列不包含is_del列名称，并且当前tableInfo的逻辑删除属性为isDel时，清空逻辑删除信息
                BeanUtil.setProperty(tableInfo, WITH_LOGIC_DELETE_PRO, false);
                BeanUtil.setProperty(tableInfo, LOGIC_DELETE_FIELD_INFO_PRO, null);
            }

            if (tableInfo.getFieldList().size() >= resultMap.getMappedProperties().size()) {
                List<TableFieldInfo> newFiledList = tableInfo.getFieldList().stream()
                    .filter(i -> resultMap.getMappedProperties().contains(i.getProperty()))
                    .collect(toList());
                BeanUtil.setProperty(tableInfo, FIELD_LIST_PRO, Collections.unmodifiableList(newFiledList));
            }

            return tableInfo;
        }

        //mapper没有配置resultMap
        if (ConfigUtils.notHasId(tableInfo.getCurrentNamespace())
            && Objects.equals(BasePO.PROPERTY_ID, tableInfo.getKeyProperty())) {
            //表不含主键，并且当前tableInfo的主键属性为id时，清空主键信息
            BeanUtil.setProperty(tableInfo, KEY_COLUMN_PRO, null);
            BeanUtil.setProperty(tableInfo, KEY_PROPERTY_PRO, null);
        }

        String logicDeleteFieldName = ConfigUtils.getLogicDeleteFieldName(tableInfo.getCurrentNamespace());
        if (Func.isNotEmpty(logicDeleteFieldName)) {
            BeanUtil.setProperty(tableInfo, WITH_LOGIC_DELETE_PRO, true);
            for (TableFieldInfo tableFieldInfo : tableInfo.getFieldList()) {
                if (tableFieldInfo.getProperty().equals(logicDeleteFieldName)) {
                    BeanUtil.setProperty(tableInfo, LOGIC_DELETE_FIELD_INFO_PRO, tableFieldInfo);
                    break;
                }
            }
        }

        return tableInfo;
    }

    @Override
    public List<AbstractMethod> getMethodList(final Class<?> mapperClass) {
        return Stream.of(
            new Insert(),
            new Delete(),
            new DeleteByMap(),
            new DeleteById(),
            new DeleteBatchByIds(),
            new Update(),
            new UpdateById(),
            new SelectById(),
            new SelectBatchByIds(),
            new SelectByMap(),
            new SelectOne(),
            new SelectCount(),
            new SelectMaps(),
            new SelectMapsPage(),
            new SelectObjs(),
            new SelectList(),
            new SelectPage(),
            new DeleteByIdPhysically(),
            new DeleteByMapPhysically(),
            new DeletePhysically(),
            new DeleteBatchIdsPhysically()
        ).collect(toList());
    }
}
