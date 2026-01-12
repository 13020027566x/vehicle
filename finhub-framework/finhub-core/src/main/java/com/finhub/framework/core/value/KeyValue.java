package com.finhub.framework.core.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Key Value 的键值对
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KeyValue<K, V> {

    private K key;
    private V value;
}
