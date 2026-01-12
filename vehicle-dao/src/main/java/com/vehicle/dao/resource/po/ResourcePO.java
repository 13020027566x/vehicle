package com.vehicle.dao.resource.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 菜单
 *
 * @author Mickey
 * @version 1.0
 * @since 2018/06/06 14:48
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "su_resource", resultMap = "ResourceResultMap")
public class ResourcePO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "su_resource";

    public static final String DB_COL_NAME = "name";

    public static final String DB_COL_CODE = "code";

    public static final String DB_COL_URL = "url";

    public static final String DB_COL_ICON = "icon";

    public static final String DB_COL_REMARK = "remark";

    public static final String DB_COL_PID = "pid";

    public static final String DB_COL_SORT = "sort";

    public static final String DB_COL_IS_MENU = "is_menu";

    public static final String DB_COL_IS_LEAF = "is_leaf";


    /**
     * 名称
     */
    private String name;

    /**
     * 编码
     */
    private String code;

    /**
     * URL
     */
    private String url;

    /**
     * 图标
     */
    private String icon;

    /**
     * 备注/描述
     */
    private String remark;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 是否菜单:0=否,1=是
     */
    private Integer isMenu;

    /**
     * 是否叶子节点:0=否,1=是
     */
    private Integer isLeaf;

}
