package ${conf.rpcApiPackageName}.demo;

import ${conf.rpcApiPackageName}.demo.dto.DemoReqDTO;
import ${conf.rpcApiPackageName}.demo.dto.DemoResDTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

/**
 * 用户服务 RPC Service 接口
 *
 * @author ${conf.author}
 * @version ${conf.version}
 * @since ${conf.createDate}
 */
public interface DemoService {

    /**
     * 根据指定用户编号，获得用户信息
     *
     * @param id 用户编号
     * @return 用户信息
     * @throws com.finhub.framework.exception.BaseException Dubbo RPC 接口签名 一律务必抛出该异常基类
     */
    DemoResDTO get(@Min(value = 1L, message = "{get.id.Min}") Integer id);

    /**
     * 添加用户信息
     *
     * @param demoReqDTO
     * @return
     * @throws com.finhub.framework.exception.BaseException
     */
    DemoResDTO add(@Valid DemoReqDTO demoReqDTO);
}
