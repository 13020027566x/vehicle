package ${conf.providerPackageName}.demo.impl;

import ${conf.rpcApiPackageName}.demo.DemoService;
import ${conf.rpcApiPackageName}.demo.dto.DemoReqDTO;
import ${conf.rpcApiPackageName}.demo.dto.DemoResDTO;
import ${conf.providerPackageName}.demo.converter.DemoConverter;
import ${conf.providerPackageName}.demo.manager.DemoManager;
import ${conf.providerPackageName}.demo.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.validation.annotation.Validated;

/**
 * DemoServiceImpl
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Validated
@DubboService(version = "${r'${'}dubbo.provider.DemoService.version}")
public class DemoServiceImpl implements DemoService {

    @Override
    public DemoResDTO get(Integer id) {
        DemoDTO demoDTO = DemoManager.me().get(id);

        return DemoConverter.me().convertToDemoResDTO(demoDTO);
    }

    @Override
    public DemoResDTO add(DemoReqDTO demoReqDTO) {
        DemoDTO demoDTO = DemoConverter.me().convertToDemoDTO(demoReqDTO);

        demoDTO = DemoManager.me().add(demoDTO);

        return DemoConverter.me().convertToDemoResDTO(demoDTO);
    }
}
