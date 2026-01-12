package ${conf.providerPackageName}.demo.manager;

import com.finhub.framework.exception.BaseException;

import cn.hutool.extra.spring.SpringUtil;
import ${conf.providerPackageName}.demo.domain.DemoDO;
import ${conf.providerPackageName}.demo.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Demo Manager
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Component
public class DemoManager {

    public static DemoManager me() {
        return SpringUtil.getBean(DemoManager.class);
    }

    public DemoDTO get(Integer id) throws BaseException {

        return DemoDO.me().transferDemoDTO(id);
    }

    public DemoDTO add(DemoDTO demoDTO) {
        return DemoDO.me().transferDemoDTO(demoDTO);
    }
}
