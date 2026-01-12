package ${conf.rpcClientPackageName}.demo.manager;

import cn.hutool.extra.spring.SpringUtil;
import ${conf.rpcApiPackageName}.demo.DemoService;
import ${conf.rpcApiPackageName}.demo.dto.DemoReqDTO;
import ${conf.rpcApiPackageName}.demo.dto.DemoResDTO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * Demo Manager
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Component
@Validated
public class DemoManager implements DemoService {

    public static DemoManager me() {
        return SpringUtil.getBean(DemoManager.class);
    }

    @DubboReference(version = "${r'${'}dubbo.consumer.DemoService.version}")
    private DemoService demoService;

    @Override
    public DemoResDTO get(Integer id) {
        return demoService.get(id);
    }

    @Override
    public DemoResDTO add(DemoReqDTO demoReqDTO) {
        return demoService.add(demoReqDTO);
    }
}
