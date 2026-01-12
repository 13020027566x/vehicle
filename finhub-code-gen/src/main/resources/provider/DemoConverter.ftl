package ${conf.providerPackageName}.demo.converter;

import com.finhub.framework.core.converter.BaseConverterConfig;

import cn.hutool.extra.spring.SpringUtil;
import ${conf.rpcApiPackageName}.demo.dto.DemoReqDTO;
import ${conf.rpcApiPackageName}.demo.dto.DemoResDTO;
import ${conf.providerPackageName}.demo.dto.DemoDTO;
import org.mapstruct.Mapper;

/**
 * Demo Converter
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Mapper(config = BaseConverterConfig.class)
public interface DemoConverter {

    static DemoConverter me() {
        return SpringUtil.getBean(DemoConverter.class);
    }

    DemoResDTO convertToDemoResDTO(DemoDTO demoDTO);

    DemoDTO convertToDemoDTO(DemoReqDTO demoReqDTO);
}
