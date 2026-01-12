package com.finhub.framework.core.converter;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * PO 与 DTO Converter
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
public interface BaseConverter<T, P> {

    /**
     * PO 转 DTO
     *
     * @param po
     * @return
     */
    T poToDTO(P po);

    /**
     * PO List 转 DTO List
     *
     * @param poList
     * @return
     */
    @InheritConfiguration(name = "poToDTO")
    List<T> poToDTOList(List<P> poList);

    /**
     * DTO 转 PO
     *
     * @param dto
     * @return
     */
    @InheritInverseConfiguration(name = "poToDTO")
    P dtoToPO(T dto);

    /**
     * DTO List 转 PO List
     *
     * @param dtoList
     * @return
     */
    @InheritConfiguration(name = "dtoToPO")
    List<P> dtoToPOList(List<T> dtoList);

    /**
     * 更新属性
     *
     * @param dto
     * @param po
     */
    @InheritConfiguration(name = "dtoToPO")
    void updatePO(T dto, @MappingTarget P po);

    /**
     * 反向，更新属性
     *
     * @param dto
     * @param po
     */
    @InheritConfiguration(name = "poToDTO")
    void updateDto(P po, @MappingTarget T dto);
}
