package com.finhub.framework.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/28 16:45
 */
@Data
public class ItemsData<T> implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 数据集合
     */
    private Collection<T> items;
}
