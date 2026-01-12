package com.finhub.framework.web.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>错误信息数据</p>
 *
 * @author Mickey
 * @date 2019/5/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMessage implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * @See MessageCodeTypeEnum.class
     * code码对应的type类型，兼容老相应体
     */
    private Integer type;

    /**
     * 弹框提示标题 兼容老相应体
     */
    private String title;

    /**
     * FBException异常兼容
     */
    private Object data;

    /**
     * code码对应消息内容
     */
    private String message;


    public ResponseMessage(String message) {
        this.message = message;
    }

}
