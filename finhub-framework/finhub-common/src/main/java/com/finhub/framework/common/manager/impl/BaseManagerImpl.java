package com.finhub.framework.common.manager.impl;

import com.finhub.framework.common.manager.BaseManager;
import com.finhub.framework.core.Func;
import com.finhub.framework.core.converter.BaseConverter;
import com.finhub.framework.core.enums.ResponseCodeEnum;
import com.finhub.framework.core.page.Page;
import com.finhub.framework.core.thread.ThreadLocalUtils;
import com.finhub.framework.core.web.dto.CurrentUser;
import com.finhub.framework.core.web.dto.Device;
import com.finhub.framework.exception.constant.enums.BusinessResponseEnum;
import com.finhub.framework.mybatis.dao.BaseDAO;
import com.finhub.framework.mybatis.po.BasePO;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * BaseManagerImpl
 *
 * @param <D> DAO
 * @param <P> PO
 * @param <T> DTO
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public abstract class BaseManagerImpl<D extends BaseDAO<P>, P extends BasePO, T, C extends BaseConverter<T, P>>
    extends ServiceImpl<D, P> implements BaseManager<P, T> {

    private static final String ID = "id";

    @Autowired
    protected C converter;

    @Override
    public Boolean saveDTO(T dto) {
        if (Func.isEmpty(dto)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dto is null or empty or empty while invoke save() method");
        }

        P po = dtoToPO(dto);
        boolean result = super.save(po);

        if (result) {
            BeanUtil.setProperty(dto, ID, po.getId());
        }
        return result;
    }

    @Override
    public Boolean saveMap(final Map<String, Object> dtoMap) {
        if (Func.isEmpty(dtoMap)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dtoMap is null or empty or empty while invoke save() method");
        }

        P po = mapToPO(dtoMap);
        boolean result = super.save(po);

        if (result) {
            dtoMap.put(ID, po.getId());
        }
        return result;
    }

    @Override
    public Boolean saveAllColumn(T dto) {
        if (Func.isEmpty(dto)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dto is null or empty or empty while invoke saveAllColumn() method");
        }

        P po = dtoToPO(dto);
        boolean result = super.save(po);

        if (result) {
            BeanUtil.setProperty(dto, ID, po.getId());
        }
        return result;
    }

    @Override
    public Boolean saveBatchAllColumn(final List<T> dtos) {
        if (Func.isEmpty(dtos)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dtos is null or empty while invoke saveBatchAllColumn() method");
        }

        List<P> poList = Lists.newArrayList();
        for (T dto : dtos) {
            poList.add(dtoToPO(dto));
        }

        boolean result = super.saveBatch(poList);
        if (result) {
            for (int i = 0; i < dtos.size(); i++) {
                BeanUtil.setProperty(dtos.get(i), ID, poList.get(i).getId());
            }
        }
        return result;
    }

    @Override
    public Boolean saveAllColumn(final Map<String, Object> dtoMap) {
        if (Func.isEmpty(dtoMap)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dtoMap is null or empty while invoke saveAllColumn() method");
        }

        P po = mapToPO(dtoMap);
        boolean result = super.save(po);

        if (result) {
            dtoMap.put(ID, po.getId());
        }
        return result;
    }

    @Override
    public Boolean removeById(final String id) {
        if (Func.isNullOrZero(id)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "id is null or empty while invoke removeById() method");
        }

        return super.removeById(id);
    }

    @Override
    public Boolean remove(T dto) {
        if (Func.isEmpty(dto)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dto is null or empty while invoke remove() method");
        }

        P po = dtoToPO(dto);

        Map<String, Object> paramsMap = Maps.newHashMap();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(po.getClass());
        ResultMap resultMap = tableInfo.getConfiguration().getResultMap(tableInfo.getResultMap());
        for (ResultMapping resultMapping : resultMap.getPropertyResultMappings()) {
            String column = resultMapping.getColumn();
            if (BasePO.DB_COL_IS_DEL.equalsIgnoreCase(column)) {
                continue;
            }

            String property = resultMapping.getProperty();
            Object value = BeanUtil.getProperty(po, property);
            if (value == null) {
                continue;
            }

            if (tableInfo.isUnderCamel()) {
                paramsMap.put(column, value);
            } else {
                paramsMap.put(property, value);
            }
        }

        return remove(paramsMap);
    }

    @Override
    public Boolean remove(final Map<String, Object> params) {
        if (Func.isEmpty(params)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "params is null or empty while invoke remove() method");
        }

        return super.removeByMap(params);
    }

    @Override
    public Boolean removeByWrapper(final Wrapper<P> wrapper) {
        if (Func.isEmpty(wrapper)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "wrapper is null or empty while invoke removeByWrapper() method");
        }

        return super.remove(wrapper);
    }

    @Override
    public Boolean removeBatchIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "ids is null or empty while invoke removeBatchIds() method");
        }

        return super.removeByIds(ids);
    }

    @Override
    public Boolean modifyById(T dto) {
        if (Func.isEmpty(dto)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dto is null or empty while invoke modifyById() method");
        }

        return super.updateById(dtoToPO(dto));
    }

    @Override
    public Boolean modifyById(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "map is null or empty while invoke modifyById() method");
        }

        return super.updateById(mapToPO(map));
    }

    @Override
    public Boolean modifyAllColumnById(T dto) {
        if (Func.isEmpty(dto)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dto is null or empty while invoke modifyAllColumnById() method");
        }

        return super.updateById(dtoToPO(dto));
    }

    @Override
    public Boolean modifyAllColumnById(final Map<String, Object> map) {
        if (Func.isEmpty(map)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "map is null or empty while invoke modifyAllColumnById() method");
        }

        return super.updateById(mapToPO(map));
    }

    @Override
    public Boolean modify(T dto, final Map<String, Object> whereMap) {
        if (Func.isEmpty(whereMap)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "whereMap is null or empty while invoke modify() method");
        }

        return super.update(dtoToPO(dto), new UpdateWrapper<P>().allEq(whereMap));
    }

    @Override
    public Boolean modify(T dto, final Wrapper<P> wrapper) {
        if (Func.isEmpty(wrapper)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "wrapper is null or empty while invoke modify() method");
        }

        return super.update(dtoToPO(dto), wrapper);
    }

    @Override
    public T findById(final String id) {
        if (Func.isEmpty(id)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "id is null or empty while invoke findById() method");
        }

        P po = super.getById(id);
        return poToDTO(po);
    }

    @Override
    public List<T> findBatchIds(final List<String> ids) {
        if (Func.isEmpty(ids)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "ids is null or empty while invoke findBatchIds() method");
        }

        return poToDTOList(super.listByIds(ids));
    }

    @Override
    public List<T> findByMap(final Map<String, Object> params) {
        return poToDTOList(super.listByMap(params));
    }

    @Override
    public T findOne(T dto) {
        if (Func.isEmpty(dto)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "dto is null or empty while invoke findOne() method");
        }

        P po = super.getOne(new QueryWrapper<>(dtoToPO(dto)));
        return poToDTO(po);
    }

    @Override
    public T findOne(final Wrapper<P> wrapper) {
        if (Func.isEmpty(wrapper)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "wrapper is null or empty while invoke findOne() method");
        }

        P po = super.getOne(wrapper);
        return poToDTO(po);
    }

    @Override
    public T findOne(final Map<String, Object> params) {
        if (Func.isEmpty(params)) {
            BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
                "params is null or empty while invoke findOne() method");
        }

        P po = super.getOne(new QueryWrapper<>(mapToPO(params)));
        return poToDTO(po);
    }

    @Override
    public Integer findCount(T dto) {
        return super.count(new QueryWrapper<>(dtoToPO(dto)));
    }

    @Override
    public Integer findCount(final Map<String, Object> params) {
        return super.count(new QueryWrapper<>(mapToPO(params)));
    }

    @Override
    public Integer findCount(final Wrapper<P> wrapper) {
        return super.count(wrapper);
    }

    @Override
    public List<T> findList(T dto) {
        List<P> poList = super.list(new QueryWrapper<>(dtoToPO(dto)));
        return poToDTOList(poList);
    }

    @Override
    public List<T> findList(final Map<String, Object> params) {
        List<P> poList = super.list(new QueryWrapper<>(mapToPO(params)));
        return poToDTOList(poList);
    }

    @Override
    public List<T> findList(final Wrapper<P> wrapper) {
        List<P> poList = super.list(wrapper);
        return poToDTOList(poList);
    }

    @Override
    public List<Map<String, Object>> findMaps(T dto) {
        return super.listMaps(new QueryWrapper<>(dtoToPO(dto)));
    }

    @Override
    public List<Map<String, Object>> findMaps(final Map<String, Object> params) {
        return super.listMaps(new QueryWrapper<>(mapToPO(params)));
    }

    @Override
    public List<Map<String, Object>> findMaps(final Wrapper<P> wrapper) {
        return super.listMaps(wrapper);
    }

    @Override
    public List<Object> findObjs(T dto) {
        return super.listObjs(new QueryWrapper<>(dtoToPO(dto)));
    }

    @Override
    public List<Object> findObjs(final Map<String, Object> params) {
        return super.listObjs(new QueryWrapper<>(mapToPO(params)));
    }

    @Override
    public Object findObj(T dto) {
        return super.getObj(new QueryWrapper<>(dtoToPO(dto)), null);
    }

    @Override
    public Object findObj(final Map<String, Object> params) {
        return super.getObj(new QueryWrapper<>(mapToPO(params)), null);
    }

    @Override
    public Object findObj(final Wrapper<P> wrapper) {
        return super.getObj(wrapper, null);
    }

    @Override
    public List<Object> findObjs(final Wrapper<P> wrapper) {
        return super.listObjs(wrapper);
    }

    @Override
    public Page<T> findPage(T dto, final Integer current, final Integer size) {
        IPage<P> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(current, size);
        page = super.page(page, new QueryWrapper<>(dtoToPO(dto)));

        return new Page<T>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(poToDTOList(page.getRecords()));
    }

    @Override
    public Page<T> findPage(final Map<String, Object> params, final Integer current, final Integer size) {
        IPage<P> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(current, size);
        page = super.page(page, new QueryWrapper<>(mapToPO(params)));

        return new Page<T>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(poToDTOList(page.getRecords()));
    }

    @Override
    public Page<T> findPage(final Wrapper<P> wrapper, final Integer current, final Integer size) {
        IPage<P> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(current, size);
        page = super.page(page, wrapper);

        return new Page<T>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(poToDTOList(page.getRecords()));
    }

    @Override
    public Page<Map<String, Object>> findMapsPage(T dto, final Integer current, final Integer size) {
        IPage<Map<String, Object>> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(current, size);
        page = super.pageMaps(page, new QueryWrapper<>(dtoToPO(dto)));

        return new Page<Map<String, Object>>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(page.getRecords());
    }

    @Override
    public Page<Map<String, Object>> findMapsPage(final Map<String, Object> params, final Integer current,
        final Integer size) {
        IPage<Map<String, Object>> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(current, size);
        page = super.pageMaps(page, new QueryWrapper<>(mapToPO(params)));

        return new Page<Map<String, Object>>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(page.getRecords());
    }

    @Override
    public Page<Map<String, Object>> findMapsPage(final Wrapper<P> wrapper, final Integer current,
        final Integer size) {
        IPage<Map<String, Object>> page = new com.baomidou.mybatisplus.extension.plugins.pagination.Page(current, size);
        page = super.pageMaps(page, wrapper);

        return new Page<Map<String, Object>>(page.getCurrent(), page.getSize(), page.getTotal()).setRecords(page.getRecords());
    }

    /**
     * 获取当前登录User信息
     *
     * @return
     */
    protected final CurrentUser getCurrentUser() {
        return ThreadLocalUtils.getCurrentUser();
    }

    /**
     * 获取当前登录User设备信息
     *
     * @return
     */
    protected final Device getDevice() {
        return ThreadLocalUtils.getDevice();
    }

    /**
     * poToDTOList
     *
     * @param poList
     * @return
     */
    protected List<T> poToDTOList(List<P> poList) {
        return converter.poToDTOList(poList);
    }

    /**
     * poToDTO
     *
     * @param po
     * @return
     */
    protected T poToDTO(P po) {
        return converter.poToDTO(po);
    }

    /**
     * dtoToPOList
     *
     * @param dtoList
     * @return
     */
    protected List<P> dtoToPOList(List<T> dtoList) {
        return converter.dtoToPOList(dtoList);
    }

    /**
     * dtoToPO
     *
     * @param dto
     * @return
     */
    protected P dtoToPO(T dto) {
        return converter.dtoToPO(dto);
    }

    /**
     * mapToPO
     *
     * @param map
     * @return
     */
    protected P mapToPO(Map<String, Object> map) {
        BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
            "mapToPO not implement, please override this method in subclass.");
        return null;
    }

    /**
     * mapToDTO
     *
     * @param map
     * @return
     */
    protected T mapToDTO(Map<String, Object> map) {
        BusinessResponseEnum.COMMON_ERROR.newException(ResponseCodeEnum.INTERNAL_ERROR,
            "mapToDTO not implement, please override this method in subclass.");
        return null;
    }
}
