package com.finhub.framework.mybatis.po;

import lombok.Data;

/**
 * @author : liuwei
 * @date : 2022/6/21
 * @desc : 该类主要作用如下场景：
 *           1：数据库表没有主键ID，清除基地车id主键属性。
 *           2：数据库表主键列名称不是ID，而是其他字段，清除基地车id主键属性
 */
@Data
public class RedefineIdBasePO extends BasePO {

    private String id;

}
