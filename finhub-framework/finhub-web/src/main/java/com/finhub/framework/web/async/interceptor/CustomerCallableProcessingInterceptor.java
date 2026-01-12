package com.finhub.framework.web.async.interceptor;

import com.finhub.framework.core.Func;
import com.finhub.framework.web.async.entity.TimeoutResult;
import com.finhub.framework.web.async.helper.WebAsyncHelper;
import com.finhub.framework.web.vo.Result;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.CallableProcessingInterceptor;

import java.util.concurrent.Callable;

import jakarta.servlet.http.HttpServletRequest;

import static com.finhub.framework.web.async.WebAsyncAssistSupport.REQUEST_TIMEOUT_RESULT_ATTRIBUTE;


/**
 * @author : liuwei
 * @date : 2022/1/24
 * @desc :
 */
@Slf4j
public class CustomerCallableProcessingInterceptor implements CallableProcessingInterceptor {

    @Override
    public <T> Object handleTimeout(NativeWebRequest request, Callable<T> task) throws Exception {
        Object nativeRequest = request.getNativeRequest();
        if (nativeRequest instanceof HttpServletRequest) {
            Object attribute = ((HttpServletRequest) nativeRequest).getAttribute(REQUEST_TIMEOUT_RESULT_ATTRIBUTE);
            if (Func.isNotNull(attribute) && attribute instanceof TimeoutResult) {
                return Result.instanceFail(((TimeoutResult) attribute).getCode(), ((TimeoutResult) attribute).getMsg());
            }
        }
        return Result.instanceFail(WebAsyncHelper.DEFAULT_TIMEOUT_CODE, WebAsyncHelper.DEFAULT_TIMEOUT_MESSAGE);
    }

}
