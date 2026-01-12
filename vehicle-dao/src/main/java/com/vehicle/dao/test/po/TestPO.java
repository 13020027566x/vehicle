package com.vehicle.dao.test.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 测试 PO
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2021-09-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bd_test", resultMap = "TestResultMap")
public class TestPO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "bd_test";

    public static final String DB_COL_TEST_NAME = "test_name";

    public static final String DB_COL_REMARK = "remark";


    /**
     * 测试名称
     */
    private String testName;

    /**
     * 备注
     */
    private String remark;

}
