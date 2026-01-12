package com.finhub.framework.exception.constant.enums;

import com.finhub.framework.exception.assertion.ServletExceptionAssert;
import com.finhub.framework.i18n.manager.MessageSourceManager;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * <p>Web Servlet 异常状态码枚举（4000+）</p>
 *
 * @author Mickey
 * @date 2019/5/2
 * @see org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
 */
@Getter
@AllArgsConstructor
public enum ServletResponseEnum implements ServletExceptionAssert {

    /**
     * 通用 Web 异常
     */
    COMMON_ERROR(4000, "通用 Web Servlet 异常", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),

    MethodArgumentNotValidException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    MethodArgumentTypeMismatchException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    MissingServletRequestPartException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    MissingPathVariableException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    BindException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    MissingServletRequestParameterException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    TypeMismatchException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    ServletRequestBindingException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    HttpMessageNotReadableException(4400, "", HttpServletResponse.SC_BAD_REQUEST),
    NoHandlerFoundException(4404, "", HttpServletResponse.SC_NOT_FOUND),
    NoSuchRequestHandlingMethodException(4404, "", HttpServletResponse.SC_NOT_FOUND),
    HttpRequestMethodNotSupportedException(4405, "", HttpServletResponse.SC_METHOD_NOT_ALLOWED),
    HttpMediaTypeNotAcceptableException(4406, "", HttpServletResponse.SC_NOT_ACCEPTABLE),
    HttpMediaTypeNotSupportedException(4415, "", HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE),
    ConversionNotSupportedException(4500, "", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    HttpMessageNotWritableException(4500, "", HttpServletResponse.SC_INTERNAL_SERVER_ERROR),
    AsyncRequestTimeoutException(4503, "", HttpServletResponse.SC_SERVICE_UNAVAILABLE);

    /**
     * 返回码，目前与{@link #statusCode}相同
     */
    private final int code;
    /**
     * 返回信息，直接读取异常的message
     */
    private final String message;
    /**
     * HTTP状态码
     */
    private final int statusCode;

    @Override
    public String getMessage() {
        return MessageSourceManager.me().getMessage("ServletResponseEnum." + this.name(), this.message);
    }
}
