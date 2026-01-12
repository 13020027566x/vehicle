package com.finhub.framework.cache.cachemanager.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author : liuwei
 * @date : 2021/11/3
 * @desc :
 */
public interface CacheServiceTest extends Serializable{

    Test getById(String id);

    Test putTest(String id);

    String oneMinute(String id);
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Test implements Serializable {

        private String id;

        private Integer age;

        private String address;
    }

}
