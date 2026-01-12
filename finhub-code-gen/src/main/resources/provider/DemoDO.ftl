package ${conf.providerPackageName}.demo.domain;

import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.constant.enums.BusinessResponseEnum;

import cn.hutool.extra.spring.SpringUtil;
import com.vehicle.provider.demo.dto.DemoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Demo DO
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
@Slf4j
@Component
public class DemoDO {

    public static DemoDO me() {
        return SpringUtil.getBean(DemoDO.class);
    }

    public DemoDTO transferDemoDTO(Integer id) throws BaseException {
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setId(id);
        demoDTO.setName("没有昵称：" + id);
        // 1 - 男；2 - 女
        demoDTO.setGender(id % 2 + 1);

        BusinessResponseEnum.COMMON_ERROR.assertIsNull(new DemoDTO());
        return demoDTO;
    }

    public DemoDTO transferDemoDTO(DemoDTO demoDTO) {
        return demoDTO;
    }
}
