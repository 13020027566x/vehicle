package com.finhub.framework.core.domain;

import com.finhub.framework.core.converter.BaseConverter;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * BaseDO
 *
 * @param <T> DTO
 * @param <P> PO
 * @param <C> Converter
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public class BaseDO<T, P, C extends BaseConverter<T, P>> {

    @Autowired
    protected C converter;
}
