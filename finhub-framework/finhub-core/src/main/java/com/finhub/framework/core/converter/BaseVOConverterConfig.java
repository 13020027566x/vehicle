package com.finhub.framework.core.converter;

import org.mapstruct.Builder;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

/**
 * VO 与 DTO Converter 配置
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/02 17:10
 */
@MapperConfig(
    componentModel = "spring",
    builder = @Builder(disableBuilder = true),
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface BaseVOConverterConfig {
}
