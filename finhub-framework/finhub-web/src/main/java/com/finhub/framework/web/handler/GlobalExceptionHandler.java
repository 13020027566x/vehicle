package com.finhub.framework.web.handler;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.environment.EnvConfig;
import com.finhub.framework.exception.ArgumentException;
import com.finhub.framework.exception.BaseException;
import com.finhub.framework.exception.BusinessException;
import com.finhub.framework.exception.ClientException;
import com.finhub.framework.exception.CommonException;
import com.finhub.framework.exception.MessageException;
import com.finhub.framework.exception.ServletException;
import com.finhub.framework.exception.UtilException;
import com.finhub.framework.exception.constant.enums.ArgumentResponseEnum;
import com.finhub.framework.exception.constant.enums.CommonResponseEnum;
import com.finhub.framework.exception.constant.enums.ServletResponseEnum;
import com.finhub.framework.exception.handler.AbstractExceptionHandler;
import com.finhub.framework.exception.util.ExceptionUtils;
import com.finhub.framework.web.vo.ResponseResult;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

/**
 * <p>全局异常处理器</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Data
@Slf4j
@RestControllerAdvice
@ConditionalOnWebApplication
public class GlobalExceptionHandler extends AbstractExceptionHandler {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected HttpServletResponse response;

    /**
     * 自定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public ResponseResult handleBaseException(BaseException e) {
        log.warn(e.getMessage(), e);

        return new ResponseResult(e.getCodeEnum().getCode(), getResponseMessage(e), e.getCodeEnum().getType());
    }

    /**
     * Controller上一层相关异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler({
        NoHandlerFoundException.class,
        HttpRequestMethodNotSupportedException.class,
        HttpMediaTypeNotSupportedException.class,
        HttpMediaTypeNotAcceptableException.class,
        MissingPathVariableException.class,
        TypeMismatchException.class,
        HttpMessageNotReadableException.class,
        HttpMessageNotWritableException.class,
        ServletRequestBindingException.class,
        ConversionNotSupportedException.class,
        MissingServletRequestPartException.class,
        AsyncRequestTimeoutException.class
    })
    public ResponseResult handleServletException(Exception e) {
        log.error(e.getMessage(), e);

        int code = CommonResponseEnum.SERVER_ERROR.getCode();
        try {
            ServletResponseEnum servletExceptionEnum = ServletResponseEnum.valueOf(e.getClass().getSimpleName());
            code = servletExceptionEnum.getCode();
        } catch (IllegalArgumentException ex) {
            log.error("class not defined in enum. [class={}, enum={}]", e.getClass().getName(), ServletResponseEnum.class.getName(), ex);
        }

        return transferResponseResult(code, e, null);
    }

    /**
     * 参数绑定异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = BindException.class)
    public ResponseResult handleBindException(BindException e) {
        log.warn("参数绑定异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseResult handleValidException(MethodArgumentNotValidException e) {
        log.warn("参数校验异常", e);

        return wrapperBindingResult(e.getBindingResult());
    }

    /**
     * 处理 MissingServletRequestParameterException 异常，参数缺失
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseResult handleMissingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
        log.error("参数缺失异常", e);

        return new ResponseResult(ArgumentResponseEnum.MISSING_PARAM.getCode(), ArgumentResponseEnum.MISSING_PARAM.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseResult handleConstraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error("参数校验异常", e);

        // 拼接错误
        StringBuilder detailMessage = new StringBuilder();
        for (ConstraintViolation<?> constraintViolation : e.getConstraintViolations()) {
            // 使用 ; 分隔多个错误
            if (detailMessage.length() > 0) {
                detailMessage.append(S_SEMICOLON);
            }
            // 拼接内容到其中，此处无需在 i18n 国际化
            // org.hibernate.validator.messageinterpolation.AbstractMessageInterpolator.interpolateMessage 已进行 i18n 国际化处理
            detailMessage.append(constraintViolation.getMessage());
        }

        return new ResponseResult(ArgumentResponseEnum.INVALID_ERROR.getCode(), detailMessage.toString());
    }

    /**
     * 包装绑定异常结果
     *
     * @param bindingResult 绑定结果
     * @return 异常结果
     */
    private ResponseResult wrapperBindingResult(BindingResult bindingResult) {
        StringBuilder msg = new StringBuilder();

        for (ObjectError error : bindingResult.getAllErrors()) {
            // 使用 ; 分隔多个错误
            msg.append(S_SEMICOLON);
//            if (error instanceof FieldError) {
//                msg.append(((FieldError) error).getField()).append(S_COLON);
//            }
            // 拼接内容到其中，此处无需在 i18n 国际化
            // org.springframework.validation.DataBinder.validate(java.lang.Object...) 已进行 i18n 国际化处理
            msg.append(error.getDefaultMessage() == null ? StrUtil.EMPTY : error.getDefaultMessage());
        }

        return new ResponseResult(ArgumentResponseEnum.COMMON_ERROR.getCode(), msg.substring(1));
    }

    /**
     * 未定义异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseResult handleException(Exception e) {

        ExceptionUtils.FinhubExceptionProperty property = ExceptionUtils.getFinhubExceptionProperty(e);

        int code = Func.toInt(Func.isEmpty(property) ? CommonResponseEnum.SERVER_ERROR.getCode() : property.getCode(),
            CommonResponseEnum.SERVER_ERROR.getCode());

        if (code == HttpStatus.UNAUTHORIZED.value() || Func.isNotNull(property)) {
            log.warn(e.getMessage(), e);
        } else {
            log.error(e.getMessage(), e);
        }

        return transferResponseResult(code, e, property);
    }

    private ResponseResult transferResponseResult(int code, Exception e,
        ExceptionUtils.FinhubExceptionProperty property) {

        boolean isFinhubException = Func.isNotNull(property);
        if (ignoreRunEnvironment() && !isFinhubException) {
            // 当为生产环境, 不适合把具体的异常信息展示给用户, 比如数据库异常信息.
            BaseException baseException = new BaseException(CommonResponseEnum.SERVER_ERROR);
            String message = getResponseMessage(baseException);
            return new ResponseResult(code, message);
        }

        ResponseResult responseResult = new ResponseResult(code, e.getMessage());
        if (isFinhubException) {
            this.compatibleFinhubException(property, responseResult, code);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return responseResult;
    }



    /**
     * 兼容 finhub 异常，增加 type 以及 title 属性
     *
     * @param property
     * @param responseResult
     * @param code
     */
    private void compatibleFinhubException(ExceptionUtils.FinhubExceptionProperty property,
        ResponseResult responseResult, int code) {
        // 设置老项目特殊属性
        responseResult.getData().setType(ExceptionUtils.getCodeType(property.getType(), code));
        responseResult.getData().setTitle(property.getTitle());
        responseResult.getData().setData(property.getData());
        if (code == HttpStatus.UNAUTHORIZED.value()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
        }
    }

    /**
     * 参数异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = ArgumentException.class)
    public ResponseResult handleArgumentException(ArgumentException e) {
        log.error(e.getMessage(), e);

        return new ResponseResult(e.getCodeEnum().getCode(), getResponseMessage(e), e.getCodeEnum().getType());
    }

    /**
     * 业务异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult handleBusinessException(BusinessException e) {
        log.warn(e.getMessage(), e);

        return new ResponseResult(e.getCodeEnum().getCode(), getResponseMessage(e), e.getCodeEnum().getType());
    }

    /**
     * Client 异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = ClientException.class)
    public ResponseResult handleClientException(ClientException e) {
        log.error(e.getMessage(), e);

        return new ResponseResult(e.getCodeEnum().getCode(), getResponseMessage(e), e.getCodeEnum().getType());
    }

    /**
     * 通用 异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = CommonException.class)
    public ResponseResult handleCommonException(CommonException e) {
        log.error(e.getMessage(), e);

        return new ResponseResult(e.getCodeEnum().getCode(), getResponseMessage(e), e.getCodeEnum().getType());
    }

    /**
     * 消息 异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = MessageException.class)
    public ResponseResult handleMessageException(MessageException e) {
        log.warn(e.getMessage(), e);

        return new ResponseResult(e.getCodeEnum().getCode(), getResponseMessage(e), e.getCodeEnum().getType());
    }

    /**
     * Servlet 异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = ServletException.class)
    public ResponseResult handleException(ServletException e) {
        log.error(e.getMessage(), e);

        return new ResponseResult(e.getCodeEnum().getCode(), getResponseMessage(e), e.getCodeEnum().getType());
    }

    /**
     * 工具 异常
     *
     * @param e 异常
     * @return 异常结果
     */
    @ResponseBody
    @ExceptionHandler(value = UtilException.class)
    public ResponseResult handleException(UtilException e) {
        log.error(e.getMessage(), e);

        return new ResponseResult(e.getCodeEnum().getCode(), getResponseMessage(e), e.getCodeEnum().getType());
    }

    /**
     * 异常信息uat/prod运行环境统一进行处理。禁止将错误异常抛给页面
     *
     * @return
     */
    private boolean ignoreRunEnvironment() {
        return EnvConfig.me().isProd() || EnvConfig.me().isUat();
    }

}
