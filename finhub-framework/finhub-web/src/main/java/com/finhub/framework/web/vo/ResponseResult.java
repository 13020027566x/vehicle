package com.finhub.framework.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>响应返回结果</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResult extends BaseResult {

    public ResponseResult(int code, String message) {
        super.setCode(code);
        this.data = new ResponseMessage(message);
    }

    public ResponseResult(int code, String message, int type) {
        super.setCode(code);

        // 处理自定义 CodeEnum 中指定 type 情况
        ResponseMessage responseMessage = new ResponseMessage(message);
        if (type > 0) {
            responseMessage.setType(type);
        }

        this.data = responseMessage;
    }

    private ResponseMessage data;
}
