package com.finhub.framework.web.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Collection;

import jakarta.validation.constraints.NotBlank;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2021/06/28 16:47
 */
@Data
public class PageD<T> implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 当前页码
     */
    @NotBlank
    private int pageNo = 1;

    /**
     * 每页大小
     */
    @NotBlank
    private int pageSize = 10;

    /**
     * 总共页数
     */
    @NotBlank
    private long totalPage;

    /**
     * 总共条数
     */
    @NotBlank
    private long totalCount;

    /**
     * 当前页实际条数
     */
    @NotBlank
    private long recordCount;

    /**
     * 数据集合
     */
    @NotBlank
    private Collection<T> dataList;
}
