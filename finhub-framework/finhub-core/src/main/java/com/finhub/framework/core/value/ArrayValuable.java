package com.finhub.framework.core.value;

/**
 * 可生成 <T> 数组的接口
 *
 * @param <T> 类型
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public interface ArrayValuable<T> {

    /**
     * @return <T> 数组
     */
    default T[] array() {
        return null;
    }
}
