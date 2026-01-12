package com.finhub.framework.mybatis.dao;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * BaseDAO
 *
 * @param <P> PO
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public interface BaseDAO<P extends BasePO> extends BaseMapper<P> {

    /**
     * 批量插入(所有字段)
     *
     * @param poList
     * @return
     */
    Integer insertBatchAllColumn(List<P> poList);

    /**
     * 根据 ID 物理删除
     *
     * @param id
     * @return
     */
    int deleteByIdPhysically(Serializable id);

    /**
     * 根据 columnMap 条件，物理删除记录
     *
     * @param columnMap
     * @return
     */
    int deleteByMapPhysically(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);

    /**
     * 根据 queryWrapper 条件，物理删除记录
     *
     * @param queryWrapper
     * @return
     */
    int deletePhysically(@Param(Constants.WRAPPER) Wrapper<P> queryWrapper);

    /**
     * 根据 ID 集合，批量物理删除
     *
     * @param idList
     * @return
     */
    int deleteBatchIdsPhysically(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
}
