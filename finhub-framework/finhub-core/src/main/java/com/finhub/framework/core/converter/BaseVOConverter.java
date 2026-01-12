package com.finhub.framework.core.converter;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Map;

/**
 * VO 与 DTO Converter
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
public interface BaseVOConverter<T, V> {

    /**
     * VO 转 DTO
     *
     * @param vo
     * @return
     */
    T voToDTO(V vo);

    /**
     * VO List 转 DTO List
     *
     * @param voList
     * @return
     */
    @InheritConfiguration(name = "voToDTO")
    List<T> voToDTOList(List<V> voList);

    /**
     * DTO 转 VO
     *
     * @param dto
     * @return
     */
    @InheritInverseConfiguration(name = "voToDTO")
    V dtoToVO(T dto);

    /**
     * DTO List 转 VO List
     *
     * @param dtoList
     * @return
     */
    @InheritConfiguration(name = "dtoToVO")
    List<V> dtoToVOList(List<T> dtoList);

    /**
     * 更新属性
     *
     * @param dto
     * @param vo
     */
    @InheritConfiguration(name = "dtoToVO")
    void updateVO(T dto, @MappingTarget V vo);

    /**
     * 反向，更新属性
     *
     * @param dto
     * @param vo
     */
    @InheritConfiguration(name = "voToDTO")
    void updateDto(V vo, @MappingTarget T dto);

    /**
     * Map 转 VO
     *
     * @param map
     * @return
     */
    V mapToVO(Map<String, Object> map);

    /**
     * Map 转 DTO
     *
     * @param map
     * @return
     */
    T mapToDTO(Map<String, Object> map);
}
