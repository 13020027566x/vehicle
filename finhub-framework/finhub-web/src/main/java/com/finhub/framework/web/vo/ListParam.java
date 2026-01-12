package com.finhub.framework.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/28 16:41
 */
@Data
public class ListParam<T> implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 集合数据
     */
    private List<T> items;
}
