package com.finhub.framework.validator.constant;

import com.finhub.framework.validator.intarray.IntArrayValuable;

import java.util.Arrays;

/**
 * @author Mickey
 * @version 1.0
 * @since 2017/11/08 16:33
 */
public enum GenderEnum implements IntArrayValuable {

    MALE(1, "男"),
    FEMALE(2, "女");

    /**
     * 值数组
     */
    public static final int[] ARRAYS = Arrays.stream(values()).mapToInt(GenderEnum::getValue).toArray();

    /**
     * 性别值
     */
    private final Integer value;
    /**
     * 性别名
     */
    private final String name;

    GenderEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public int[] array() {
        return ARRAYS;
    }

}
