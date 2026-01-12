package com.finhub.framework.core.page;

import com.finhub.framework.core.Func;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * 分页 Page
 *
 * @param <T>
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
@Data
public class Page<T> {

    private static final long serialVersionUID = 8545996863226528798L;

    /**
     * 查询数据列表
     */
    private List<T> records = Collections.emptyList();
    /**
     * 总数
     */
    private long total = 0;
    /**
     * 每页显示条数，默认 10
     */
    private long size = 10;
    /**
     * 当前页
     */
    private long current = 1;

    public Page() {
        // to do nothing
    }

    /**
     * 分页构造函数
     *
     * @param current 当前页
     * @param size    每页显示条数
     */
    public Page(long current, long size) {
        this(current, size, 0);
    }

    public Page(long current, long size, long total) {
        if (current > 1) {
            this.current = current;
        }
        this.size = size;
        this.total = total;
    }

    public List<T> getRecords() {
        return this.records;
    }

    public Page<T> setRecords(List<T> records) {
        this.records = records;
        return this;
    }

    public long getTotal() {
        return this.total;
    }

    public Page<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getSize() {
        return this.size;
    }

    public Page<T> setSize(long size) {
        this.size = size;
        return this;
    }

    public long getCurrent() {
        return this.current;
    }

    public Page<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

    public void transfer(Page page) {
        this.setSize(page.getSize());
        this.setTotal(page.getTotal());
        this.setCurrent(page.getCurrent());
    }

    public static <T> Page<T> empty() {
        return new Page<>();
    }

    public static <T> Page<T> empty(long current, long size) {
        return new Page<>(current, size);
    }

    public static <T> Page<T> empty(long current, long size, long total) {
        return new Page<>(current, size, total);
    }

    public boolean isEmpty() {
        return Func.isEmpty(records);
    }
}
