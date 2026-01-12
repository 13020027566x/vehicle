package com.finhub.framework.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/28 16:47
 */
@Data
public class PageData<T> implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 提示信息
     */
    private String message;

    /**
     * 当前页码
     */
    private int pageNo = 1;

    /**
     * 每页大小
     */
    private int pageSize = 10;

    /**
     * 总共页数
     */
    private long totalPage;

    /**
     * 总共条数
     */
    private long totalCount;

    /**
     * 当前页实际条数
     */
    private long recordCount;

    /**
     * 数据集合
     */
    private Collection<T> items;
}
