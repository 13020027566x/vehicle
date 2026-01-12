package com.finhub.framework.web.vo;

import com.finhub.framework.core.Func;
import com.finhub.framework.web.property.ResponseProperties;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;

import static com.finhub.framework.core.number.NumberConstants.N_ZERO;
import static com.finhub.framework.core.str.StrConstants.S_EMPTY;

/**
 * swift 项目（老项目）返回web结果集结构体
 *
 * @author liuwei
 */
@Data
public class Result<T> extends BaseResult {

    private static final long serialVersionUID = 5409185459234711691L;

    private static final Object EMPTY_OBJECT = new Object();

    /**
     * 响应类型
     */
    @NotBlank
    private Integer type = ResponseProperties.me().getSuccessType();

    /**
     * app 弹框标题
     */
    @NotBlank
    private String title = ResponseProperties.me().getSuccessTitle();

    /**
     * 提示信息
     */
    @NotBlank
    private String msg = ResponseProperties.me().getSuccessMsg();

    /**
     * 返回数据结果
     */
    @NotBlank
    private T data = null;

    /**
     * 总条数
     */
    @NotBlank
    private Integer count = ResponseProperties.me().getSuccessCount();

    /**
     * 返回数据结果
     *
     * @return 返回数据结果
     */
    public Object getData() {
        return data == null && ResponseProperties.me().getFillDataObjectEnable() ? EMPTY_OBJECT : data;
    }

    /**
     * 构建响应成功结构体，不需要返回给客户端数据，只是响应成功，
     * 默认 code=200
     * 默认 msg=success
     *
     * @param <T>
     * @return 服务端响应结构体
     */
    public static <T> Result<T> instanceSuccess() {
        return instanceSuccess(null);
    }

    /**
     * 构建响应成功结构体
     * 默认 code=200
     * 默认 msg=success
     *
     * @param data 服务端响应数据
     * @param <T>
     * @return 服务端响应结构体
     */
    public static <T> Result<T> instanceSuccess(T data) {
        return instanceSuccess(data, null);
    }

    /**
     * 构建响应成功结构体
     * 默认 code=200
     * 默认 msg=success
     *
     * @param data  服务端响应数据
     * @param count 数据总条数
     * @param <T>
     * @return 服务端响应结构体
     */
    public static <T> Result<T> instanceSuccess(T data, Integer count) {
        return instanceSuccess(ResponseProperties.me().getSuccessCode(), ResponseProperties.me().getSuccessMsg(), data, count);
    }

    /**
     * 构建响应成功结构体，不需要返回给客户端数据，只是响应成功
     *
     * @param code 自定义成功状态码
     * @param <T>
     * @return 服务端响应结构体
     */
    public static <T> Result<T> instanceSuccess(int code) {
        return instanceSuccess(code, null);
    }

    /**
     * 构建响应成功结构体，不需要返回给客户端数据，只是响应成功
     *
     * @param code 自定义成功状态码
     * @param data 服务端响应数据
     * @param <T>
     * @return 服务端响应结构体
     */
    public static <T> Result<T> instanceSuccess(int code, T data) {
        return instanceSuccess(code, ResponseProperties.me().getSuccessMsg(), data);
    }

    /**
     * 构建响应成功结构体
     *
     * @param code  自定义成功状态码
     * @param data  服务端响应数据
     * @param count 总数
     * @param <T>
     * @return 服务端响应结构体
     */
    public static <T> Result<T> instanceSuccess(int code, T data, Integer count) {
        return instanceSuccess(code, ResponseProperties.me().getSuccessMsg(), data, count);
    }

    /**
     * 构建响应成功结构体
     *
     * @param code 自定义成功状态码
     * @param msg  自定义成功提示语
     * @param data 服务端响应数据
     * @param <T>
     * @return 服务端响应结构体
     */
    public static <T> Result<T> instanceSuccess(int code, String msg, T data) {
        return instanceSuccess(code, msg, data, null);
    }

    /**
     * 构建响应成功结构体
     *
     * @param code  自定义成功状态码
     * @param msg   自定义成功提示语
     * @param data  服务端响应数据
     * @param count 数据总条数
     * @param <T>
     * @return 服务端响应结构体
     */
    public static <T> Result<T> instanceSuccess(int code, String msg, T data, Integer count) {
        return convertResult(data, count, code, msg, null, null);
    }


    /**
     * 构建响应失败结构体
     * 默认code = 4000
     * 默认msg=fail
     *
     * @param <T>
     * @return
     */
    public static <T> Result<T> instanceFail() {
        return instanceFail(ResponseProperties.me().getFailCode());
    }

    /**
     * 构建响应失败结构体
     * 默认msg=fail
     *
     * @param code 自定义状态码
     * @param <T>
     * @return
     */
    public static <T> Result<T> instanceFail(Integer code) {
        return instanceFail(code, ResponseProperties.me().getFailMsg());
    }

    /**
     * 构建响应失败结构体
     *
     * @param code 自定义状态码
     * @param msg  自定义提示语
     * @param <T>
     * @return
     */
    public static <T> Result<T> instanceFail(Integer code, String msg) {
        return convertResult(code, msg);
    }

    public static <T> Result<T> convertResult(T data) {
        return convertResult(data, null);
    }

    public static <T> Result<T> convertResult(T data, Integer count) {
        return convertResult(data, count, ResponseProperties.me().getSuccessCode(), ResponseProperties.me().getSuccessMsg(),
            null, null);
    }

    public static <T> Result<T> convertResult(int code) {
        return convertResult(null, null, code, ResponseProperties.me().getSuccessMsg(), null, null);
    }

    public static <T> Result<T> convertResult(Integer code, String msg) {

        return convertResult(code, msg, null, null);
    }

    public static <T> Result<T> convertResult(T data, Integer code, String msg) {

        return convertResult(data, null, code, msg);
    }

    public static <T> Result<T> convertResult(T data, Integer count, Integer code, String msg) {

        return convertResult(data, count, code, msg, null, null);
    }

    public static <T> Result<T> convertResult(Integer code, String msg, Integer type, String title) {

        return convertResult(null, null, code, msg, type, title);
    }

    /**
     * 转换通用结果体
     *
     * @param data
     * @param count
     * @param code
     * @param msg
     * @param type
     * @param title
     * @param <T>
     * @return
     */
    public static <T> Result<T> convertResult(T data, Integer count, Integer code, String msg,
        Integer type, String title) {

        Result<T> r = new Result<>();
        r.setData(data);
        r.setCount(Func.isNull(count) ? N_ZERO : count);
        r.setCode(code);
        r.setMsg(Func.isNull(msg) ? S_EMPTY : msg);
        r.setType(Func.isNull(type) ? N_ZERO : type);
        r.setTitle(Func.isNull(title) ? S_EMPTY : title);

        return r;
    }
}
