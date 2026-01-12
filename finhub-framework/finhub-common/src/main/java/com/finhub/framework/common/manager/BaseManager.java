package com.finhub.framework.common.manager;

import com.finhub.framework.core.page.Page;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * BaseManager
 *
 * @param <P> PO
 * @param <T> DTO
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public interface BaseManager<P, T> extends IService<P> {

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param dto DTO对象
     * @return Boolean
     */
    Boolean saveDTO(T dto);

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param dtoMap
     * @return
     */
    Boolean saveMap(Map<String, Object> dtoMap);

    /**
     * <p>
     * 插入一条记录(所有字段)
     * </p>
     *
     * @param dto DTO对象
     * @return Boolean
     */
    Boolean saveAllColumn(T dto);

    /**
     * <p>
     * 插入一条记录(所有字段)
     * </p>
     *
     * @param dtoMap
     * @return
     */
    Boolean saveAllColumn(Map<String, Object> dtoMap);

    /**
     * <p>
     * 批量插入记录(所有字段)
     * </p>
     *
     * @param dtos
     * @return
     */
    Boolean saveBatchAllColumn(List<T> dtos);

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return Boolean
     */
    Boolean removeById(String id);

    /**
     * <p>
     * 根据 po 条件，删除记录
     * </p>
     *
     * @param po 实体对象
     * @return int
     */
    Boolean remove(T po);

    /**
     * 删除
     *
     * @param params
     * @return
     */
    Boolean remove(Map<String, Object> params);

    /**
     * <p>
     * 根据 po 条件，删除记录
     * </p>
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @return int
     */
    Boolean removeByWrapper(Wrapper<P> wrapper);

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param ids 主键ID列表
     * @return int
     */
    Boolean removeBatchIds(List<String> ids);

    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param dto 实体对象
     * @return int
     */
    Boolean modifyById(T dto);

    /**
     * 修改
     *
     * @param map
     * @return
     */
    Boolean modifyById(Map<String, Object> map);

    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param dto 实体对象
     * @return int
     */
    Boolean modifyAllColumnById(T dto);

    /**
     * 修改
     *
     * @param map
     * @return
     */
    Boolean modifyAllColumnById(Map<String, Object> map);

    /**
     * <p>
     * 根据 whereMap 条件，更新记录
     * </p>
     *
     * @param dto      实体对象
     * @param whereMap 条件Map
     * @return
     */
    Boolean modify(T dto, Map<String, Object> whereMap);

    /**
     * <p>
     * 根据 wherePO 条件，更新记录
     * </p>
     *
     * @param dto     DTO
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @return
     */
    Boolean modify(T dto, Wrapper<P> wrapper);

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return T
     */
    T findById(String id);

    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param ids 主键ID列表
     * @return List<T>
     */
    List<T> findBatchIds(List<String> ids);

    /**
     * <p>
     * 查询（根据 columnMap 条件）
     * </p>
     *
     * @param params 参数对象
     * @return List<T>
     */
    List<T> findByMap(Map<String, Object> params);

    /**
     * <p>
     * 根据 po 条件，查询一条记录
     * </p>
     *
     * @param po 实体对象
     * @return T
     */
    T findOne(T po);

    /**
     * 查找First
     *
     * @param params
     * @return
     */
    T findOne(Map<String, Object> params);

    /**
     * 查找First
     *
     * @param wrapper
     * @return
     */
    T findOne(Wrapper<P> wrapper);

    /**
     * <p>
     * 根据 Wrapper 条件，查询总记录数
     * </p>
     *
     * @param dto 实体对象
     * @return int
     */
    Integer findCount(T dto);

    /**
     * 查询总记录数
     *
     * @param params
     * @return
     */
    Integer findCount(Map<String, Object> params);

    /**
     * <p>
     * 根据 Wrapper 条件，查询总记录数
     * </p>
     *
     * @param wrapper 实体对象
     * @return int
     */
    Integer findCount(Wrapper<P> wrapper);

    /**
     * <p>
     * 根据 po 条件，查询全部记录
     * </p>
     *
     * @param po 实体对象
     * @return List<T>
     */
    List<T> findList(T po);

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<T> findList(Map<String, Object> params);

    /**
     * <p>
     * 根据 po 条件，查询全部记录
     * </p>
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @return List<T>
     */
    List<T> findList(Wrapper<P> wrapper);

    /**
     * <p>
     * 根据 Wrapper 条件，查询全部记录
     * </p>
     *
     * @param dto 实体对象
     * @return List<T>
     */
    List<Map<String, Object>> findMaps(T dto);

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> findMaps(Map<String, Object> params);

    /**
     * <p>
     * 根据 Wrapper 条件，查询全部记录
     * </p>
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @return List<T>
     */
    List<Map<String, Object>> findMaps(Wrapper<P> wrapper);

    /**
     * <p>
     * 根据 Wrapper 条件，查询全部记录
     * </p>
     *
     * @param dto 实体对象
     * @return List<Object>
     */
    List<Object> findObjs(T dto);

    /**
     * 查询列表
     *
     * @param params
     * @return
     */
    List<Object> findObjs(Map<String, Object> params);

    /**
     * <p>
     * 根据 Wrapper 条件，查询全部记录
     * </p>
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @return List<Object>
     */
    List<Object> findObjs(Wrapper<P> wrapper);

    /**
     * <p>
     * 根据 Wrapper 条件，查询第一个记录
     * </p>
     *
     * @param dto 实体对象
     * @return Object
     */
    Object findObj(T dto);

    /**
     * 查询对象
     *
     * @param params
     * @return
     */
    Object findObj(Map<String, Object> params);

    /**
     * 查询对象
     *
     * @param wrapper
     * @return
     */
    Object findObj(Wrapper<P> wrapper);

    /**
     * <p>
     * 根据 dto 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param dto     参数DTO
     * @param current 当前页
     * @param size    大小
     * @return List<T>
     */
    Page<T> findPage(T dto, Integer current, Integer size);

    /**
     * 查询分页
     *
     * @param params  参数MAP
     * @param current 当前页
     * @param size    大小
     * @return
     */
    Page<T> findPage(Map<String, Object> params, Integer current, Integer size);

    /**
     * <p>
     * 根据 po 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param wrapper 实体对象封装操作类（可以为 null）
     * @param current 当前页
     * @param size    大小
     * @return List<T>
     */
    Page<T> findPage(Wrapper<P> wrapper, Integer current, Integer size);

    /**
     * <p>
     * 根据 dto 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param dto     参数DTO
     * @param current 当前页
     * @param size    大小
     * @return List<Map<String, Object>>
     */
    Page<Map<String, Object>> findMapsPage(T dto, Integer current, Integer size);

    /**
     * 查询分页
     *
     * @param params  参数MAP
     * @param current 当前页
     * @param size    大小
     * @return
     */
    Page<Map<String, Object>> findMapsPage(Map<String, Object> params, Integer current, Integer size);

    /**
     * <p>
     * 根据 Wrapper 条件，查询全部记录（并翻页）
     * </p>
     *
     * @param wrapper 实体对象封装操作类
     * @param current 当前页
     * @param size    大小
     * @return List<Map<String, Object>>
     */
    Page<Map<String, Object>> findMapsPage(Wrapper<P> wrapper, Integer current, Integer size);
}
