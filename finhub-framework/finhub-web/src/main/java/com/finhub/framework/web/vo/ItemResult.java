package com.finhub.framework.web.vo;

import lombok.Data;


/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
public class ItemResult<T> extends BaseResult {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 数据
     */
    private ItemData<T> data;
}
