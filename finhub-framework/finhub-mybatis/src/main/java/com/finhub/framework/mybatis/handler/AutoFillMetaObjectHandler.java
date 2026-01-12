package com.finhub.framework.mybatis.handler;

import com.finhub.framework.core.thread.ThreadLocalUtils;
import com.finhub.framework.mybatis.config.ConfigUtils;
import com.finhub.framework.mybatis.config.DaoConfig;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.core.text.StrFormatter;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.Sequence;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

import static com.finhub.framework.core.thread.ThreadLocalUtils.getCurrentUser;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_CREATE_AT;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_CREATE_BY;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_CREATE_NAME;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_CREATE_TIME;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_ID;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_IS_DEL;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_IS_TEST;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_UPDATE_AT;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_UPDATE_BY;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_UPDATE_NAME;
import static com.finhub.framework.mybatis.po.BasePO.PROPERTY_UPDATE_TIME;

/**
 * 自动填充元属性处理器
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
@Slf4j
public class AutoFillMetaObjectHandler implements MetaObjectHandler {

    private static final Sequence SEQUENCE = new Sequence();

    @Override
    public void insertFill(final MetaObject metaObject) {
        TableInfo tableInfo = findTableInfo(metaObject);
        if (tableInfo == null) {
            return;
        }

        TableFieldInfo idDelField = null;
        TableFieldInfo isTestField = null;
        TableFieldInfo createAtField = null;
        TableFieldInfo createByField = null;
        TableFieldInfo createNameField = null;
        TableFieldInfo createTimeField = null;
        TableFieldInfo updateAtField = null;
        TableFieldInfo updateByField = null;
        TableFieldInfo updateNameField = null;
        TableFieldInfo updateTimeField = null;

        for (TableFieldInfo field : tableInfo.getFieldList()) {
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_IS_DEL)) {
                idDelField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_IS_TEST)) {
                isTestField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_CREATE_AT)) {
                createAtField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_CREATE_BY)) {
                createByField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_CREATE_NAME)) {
                createNameField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_CREATE_TIME)) {
                createTimeField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_UPDATE_AT)) {
                updateAtField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_UPDATE_BY)) {
                updateByField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_UPDATE_NAME)) {
                updateNameField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_UPDATE_TIME)) {
                updateTimeField = field;
            }
        }

        ResultMap resultMap = tableInfo.getConfiguration().getResultMap(tableInfo.getResultMap());
        if (!ConfigUtils.isAutoKey(tableInfo.getCurrentNamespace()) && resultMap.getMappedProperties().contains(PROPERTY_ID)) {
            fillStrategy(metaObject, PROPERTY_ID, generateBusinessId());
        }

        if (idDelField != null && idDelField.isWithInsertFill()) {
            strictInsertFill(metaObject, PROPERTY_IS_DEL, () -> 0, Integer.class);
        }
        if (isTestField != null && isTestField.isWithInsertFill()) {
            strictInsertFill(metaObject, PROPERTY_IS_TEST, () -> {
                if (getCurrentUser() != null) {
                    return getCurrentUser().getIsTest();
                }
                return 0;
            }, Integer.class);
        }

        long currentTimeMillis = DateUtil.current();
        Date currentTime = DateUtil.date(currentTimeMillis);

        if (createAtField != null && createAtField.isWithInsertFill()) {
            // create_at 清空强制更新
            metaObject.setValue(PROPERTY_CREATE_AT, null);
            strictInsertFill(metaObject, PROPERTY_CREATE_AT, () -> currentTimeMillis, Long.class);
        }
        if (createTimeField != null && createTimeField.isWithInsertFill()) {
            // create_time 清空强制更新
            metaObject.setValue(PROPERTY_CREATE_TIME, null);
            strictInsertFill(metaObject, PROPERTY_CREATE_TIME, () -> currentTime, Date.class);
        }
        if (createByField != null && createByField.isWithInsertFill()) {
            strictInsertFill(metaObject, PROPERTY_CREATE_BY, () -> {
                if (getCurrentUser() != null) {
                    return getCurrentUser().getId();
                }
                return null;
            }, String.class);
        }
        if (createNameField != null && createNameField.isWithInsertFill()) {
            strictInsertFill(metaObject, PROPERTY_CREATE_NAME, () -> {
                if (getCurrentUser() != null) {
                    return getCurrentUser().getName();
                }
                return null;
            }, String.class);
        }

        if (updateAtField != null && updateAtField.isWithInsertFill()) {
            // update_at 清空强制更新
            metaObject.setValue(PROPERTY_UPDATE_AT, null);
            strictInsertFill(metaObject, PROPERTY_UPDATE_AT, () -> currentTimeMillis, Long.class);
        }
        if (updateTimeField != null && updateTimeField.isWithInsertFill()) {
            // update_time 清空强制更新
            metaObject.setValue(PROPERTY_UPDATE_TIME, null);
            strictInsertFill(metaObject, PROPERTY_UPDATE_TIME, () -> currentTime, Date.class);
        }
        if (updateByField != null && updateByField.isWithInsertFill()) {
            strictInsertFill(metaObject, PROPERTY_UPDATE_BY, () -> {
                if (getCurrentUser() != null) {
                    return getCurrentUser().getId();
                }
                return null;
            }, String.class);
        }
        if (updateNameField != null && updateNameField.isWithInsertFill()) {
            strictInsertFill(metaObject, PROPERTY_UPDATE_NAME, () -> {
                if (getCurrentUser() != null) {
                    return getCurrentUser().getName();
                }
                return null;
            }, String.class);
        }
    }

    @Override
    public void updateFill(final MetaObject metaObject) {
        TableInfo tableInfo = findTableInfo(metaObject);
        if (tableInfo == null) {
            return;
        }

        TableFieldInfo updateAtField = null;
        TableFieldInfo updateByField = null;
        TableFieldInfo updateNameField = null;
        TableFieldInfo updateTimeField = null;

        for (TableFieldInfo field : tableInfo.getFieldList()) {
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_UPDATE_AT)) {
                updateAtField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_UPDATE_BY)) {
                updateByField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_UPDATE_NAME)) {
                updateNameField = field;
            }
            if (CharSequenceUtil.equals(field.getProperty(), PROPERTY_UPDATE_TIME)) {
                updateTimeField = field;
            }
        }

        long currentTimeMillis = DateUtil.current();
        Date currentTime = DateUtil.date(currentTimeMillis);

        if (updateAtField != null && updateAtField.isWithUpdateFill()) {
            // update_at 清空强制更新
            metaObject.setValue(PROPERTY_UPDATE_AT, null);
            strictUpdateFill(metaObject, PROPERTY_UPDATE_AT, () -> currentTimeMillis, Long.class);
        }
        if (updateTimeField != null && updateTimeField.isWithUpdateFill()) {
            // update_time 清空强制更新
            metaObject.setValue(PROPERTY_UPDATE_TIME, null);
            strictUpdateFill(metaObject, PROPERTY_UPDATE_TIME, () -> currentTime, Date.class);
        }
        if (updateByField != null && updateByField.isWithUpdateFill()) {
            strictUpdateFill(metaObject, PROPERTY_UPDATE_BY, () -> {
                if (getCurrentUser() != null) {
                    return getCurrentUser().getId();
                }
                return null;
            }, String.class);
        }
        if (updateNameField != null && updateNameField.isWithUpdateFill()) {
            strictUpdateFill(metaObject, PROPERTY_UPDATE_NAME, () -> {
                if (getCurrentUser() != null) {
                    return getCurrentUser().getName();
                }
                return null;
            }, String.class);
        }
    }

    /**
     * 生成自定义业务规则前缀
     *
     * @return
     */
    private String generateBusinessId() {
        String idPattern = ThreadLocalUtils.getIdPattern();
        if (CharSequenceUtil.isBlank(idPattern)) {
            idPattern = DaoConfig.me().getIdPattern();
        }
        return CharSequenceUtil.isBlank(idPattern) ? String.valueOf(SEQUENCE.nextId()) : StrFormatter.format(idPattern, SEQUENCE.nextId());
    }
}
