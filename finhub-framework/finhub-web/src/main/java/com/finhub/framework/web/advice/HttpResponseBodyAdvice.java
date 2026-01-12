package com.finhub.framework.web.advice;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.annotation.Desensitize;
import com.finhub.framework.core.annotation.Desensitizes;
import com.finhub.framework.core.number.NumberConstants;
import com.finhub.framework.core.thread.ThreadLocalUtils;
import com.finhub.framework.exception.constant.MessageCodeTypeEnum;
import com.finhub.framework.web.constant.WebConstants;
import com.finhub.framework.web.property.ResponseProperties;
import com.finhub.framework.web.vo.BaseResult;
import com.finhub.framework.web.vo.ItemData;
import com.finhub.framework.web.vo.ItemR;
import com.finhub.framework.web.vo.ItemResult;
import com.finhub.framework.web.vo.ItemsData;
import com.finhub.framework.web.vo.ItemsR;
import com.finhub.framework.web.vo.ItemsResult;
import com.finhub.framework.web.vo.MessageData;
import com.finhub.framework.web.vo.MessageResult;
import com.finhub.framework.web.vo.PageD;
import com.finhub.framework.web.vo.PageData;
import com.finhub.framework.web.vo.PageR;
import com.finhub.framework.web.vo.PageResult;
import com.finhub.framework.web.vo.ResponseMessage;
import com.finhub.framework.web.vo.ResponseResult;
import com.finhub.framework.web.vo.Result;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.finhub.framework.exception.constant.enums.ReturnCodeEnum.RC0;
import static com.finhub.framework.exception.constant.enums.ReturnCodeEnum.RC200;

/**
 * 对 @ResponseBody 注解响应进行处理
 *
 * @author Mickey
 * @version 1.0
 * @since 15-5-22 下午7:57
 */
@Data
@Slf4j
@RestControllerAdvice
@ConditionalOnWebApplication
public class HttpResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private static final String KEY_TRACE_ID = "traceId";

    private static final String KEY_REQUEST_ID = "requestId";

    @Value("${vehicle.adaptive.enabled:true}")
    private boolean adaptiveEnabled;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        log.debug("HttpResponseBodyAdvice supports. [supports={}]", true);
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body == null) {
            return null;
        }

        if (body instanceof BaseResult) {
            Integer code = ((BaseResult) body).getCode();
            if (ResponseProperties.me().getEnable() && code != null && (code == RC200.getCode() || code == RC0.getCode())) {
                // code 不为 null 且 为 0 或 200 时，处理为配置的成功码
                ((BaseResult) body).setCode(ResponseProperties.me().getSuccessCode());
            }
        }

        if (body instanceof ItemR) {
            return processDesensitizes((ItemR) body);
        }

        if (body instanceof ItemsR) {
            return processDesensitizes((ItemsR) body);
        }

        if (body instanceof PageR) {
            return processDesensitizes((PageR) body);
        }

        if (body instanceof ItemResult) {
            body = processDesensitizes((ItemResult) body);
            if (adaptiveEnabled) {
                return processResult(request, (ItemResult) body);
            }
        }

        if (body instanceof ItemsResult) {
            body = processDesensitizes((ItemsResult) body);
            if (adaptiveEnabled) {
                return processResult(request, (ItemsResult) body);
            }
        }

        if (body instanceof PageResult) {
            body = processDesensitizes((PageResult) body);
            if (adaptiveEnabled) {
                return processResult(request, (PageResult) body);
            }
        }

        if (body instanceof MessageResult && adaptiveEnabled) {
            return processResult(request, (MessageResult) body);
        }

        if (body instanceof ResponseResult && adaptiveEnabled) {
            return processResult(request, (ResponseResult) body);
        }

        if (body instanceof Map) {
            Map map = (Map) body;
            map.put(KEY_REQUEST_ID, BaseResult.getRequestId4Response());
            map.put(KEY_TRACE_ID, BaseResult.getTraceId4Response());
        }

        return setRequestAttributeAndReturn(request, body);
    }

    private Object processDesensitizes(ItemR body) {
        if (ThreadLocalUtils.getDesensitizes() == null) {
            return body;
        }

        doDesensitizes(Collections.singletonList(body.getData()));
        return body;
    }

    private Object processDesensitizes(ItemsR body) {
        if (ThreadLocalUtils.getDesensitizes() == null) {
            return body;
        }

        Collection beans = (Collection) body.getData();
        doDesensitizes(beans);
        return body;
    }

    private Object processDesensitizes(PageR body) {
        if (ThreadLocalUtils.getDesensitizes() == null) {
            return body;
        }

        Collection beans = ((PageD)body.getData()).getDataList();
        doDesensitizes(beans);
        return body;
    }

    private Object processDesensitizes(ItemResult body) {
        if (ThreadLocalUtils.getDesensitizes() == null) {
            return body;
        }

        doDesensitizes(Collections.singletonList(body.getData().getItem()));
        return body;
    }

    private Object processDesensitizes(ItemsResult body) {
        if (ThreadLocalUtils.getDesensitizes() == null) {
            return body;
        }

        doDesensitizes(body.getData().getItems());
        return body;
    }

    private Object processDesensitizes(PageResult body) {
        if (ThreadLocalUtils.getDesensitizes() == null) {
            return body;
        }

        doDesensitizes(body.getData().getItems());
        return body;
    }

    private static void doDesensitizes(Collection beans) {
        Desensitizes desensitizes = ThreadLocalUtils.getDesensitizes();
        for (Object bean : beans) {
            for (Desensitize desensitize : desensitizes.value()) {
                String value = BeanUtil.getProperty(bean, desensitize.field());
                if (Func.isNotEmpty(value)) {
                    value = DesensitizedUtil.desensitized(value, desensitize.type());
                    if (StrUtil.isNotBlank(desensitize.desensitizeField())) {
                        BeanUtil.setProperty(bean, desensitize.desensitizeField(), value);
                    } else {
                        BeanUtil.setProperty(bean, desensitize.field(), value);
                    }
                }
            }
        }
    }



    /**
     * 处理基地车单个实体
     *
     * @param request   请求连接
     * @param body      控制器响应结果
     * @return          最终响应结果
     */
    private Object processResult(ServerHttpRequest request, ItemResult body) {

        ItemData data = body.getData();
        if (Func.isNull(data)) {
            return this.getInstanceSuccessEmptyResult(request);
        }

        Result result = Result.convertResult(data.getItem(), body.getCode(), data.getMessage());

        return this.setRequestAttributeAndReturn(request, result);
    }

    /**
     * 处理基地车集合实体
     *
     * @param request   请求连接
     * @param body      控制器响应结果
     * @return          最终响应结果
     */
    private Object processResult(ServerHttpRequest request, ItemsResult body) {

        ItemsData data = body.getData();
        if (Func.isNull(data)) {
            return this.getInstanceSuccessEmptyResult(request);
        }

        Result result = Result.convertResult(data.getItems(), body.getCode(), data.getMessage());

        return this.setRequestAttributeAndReturn(request, result);
    }

    /**
     * 处理基地车程序响应指定异常结果
     *
     * @param request   请求连接
     * @param body      控制器响应结果
     * @return          最终响应结果
     */
    private Object processResult(ServerHttpRequest request, MessageResult body) {

        MessageData data = body.getData();
        if (Func.isNull(data)) {
            return this.getInstanceSuccessEmptyResult(request);
        }
        int type = MessageCodeTypeEnum.getTypeByCode(body.getCode());

        Result result = Result.convertResult(body.getCode(), data.getMessage(), type, null);

        return this.setRequestAttributeAndReturn(request, result);
    }

    /**
     * 处理基地车分页实体
     *
     * @param request   请求连接
     * @param body      控制器响应结果
     * @return          最终响应结果
     */
    private Object processResult(ServerHttpRequest request, PageResult body) {

        PageData data = body.getData();
        if (Func.isNull(data)) {
            return this.getInstanceSuccessEmptyResult(request);
        }

        Map<String, Object> dataMap = Maps.newHashMap();
        dataMap.put("totalCount", data.getTotalCount());
        dataMap.put("totalPage", data.getTotalPage());
        dataMap.put("recordCount", data.getRecordCount());
        dataMap.put("pageNo", data.getPageNo());
        dataMap.put("pageSize", data.getPageSize());
        dataMap.put("dataList", data.getItems());

        int count = Func.toInt(data.getTotalCount(), NumberConstants.N_ZERO);

        Result result = Result.convertResult(dataMap, count, body.getCode(), data.getMessage());

        return this.setRequestAttributeAndReturn(request, result);
    }

    /**
     * 处理基地车异常响应结果
     *
     * @param request   请求连接
     * @param body      控制器响应结果
     * @return          最终响应结果
     */
    private Object processResult(ServerHttpRequest request, ResponseResult body) {
        ResponseMessage data = body.getData();
        if (Func.isNull(data)) {
            return this.getInstanceSuccessEmptyResult(request);
        }
        int code = body.getCode();
        int type = (data.getType() == null || data.getType() == 0) ? MessageCodeTypeEnum.getTypeByCode(code) : data.getType();

        Result result = Result.convertResult(data.getData(), null, code, data.getMessage(), type, data.getTitle());

        return this.setRequestAttributeAndReturn(request, result);
    }

    /**
     * 获取成功请求空结果集（data为空）
     *
     * @param request   请求连接
     * @return          空结果
     */
    public Result getInstanceSuccessEmptyResult(ServerHttpRequest request) {
        return this.setRequestAttributeAndReturn(request, Result.instanceSuccess());
    }


    /**
     * 将结果设置到request attribute中，用于拦截器打印日志
     *
     * @param request
     * @param result
     * @param <T>
     * @return
     */
    public <T> T setRequestAttributeAndReturn(ServerHttpRequest request, T result) {
        if (request instanceof ServletServerHttpRequest) {
            ((ServletServerHttpRequest) request).getServletRequest().setAttribute(WebConstants.RESULT_KEY, result);
        }

        return result;
    }
}
