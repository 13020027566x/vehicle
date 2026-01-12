package com.vehicle.dao.fileattach.po;

import com.finhub.framework.mybatis.po.BasePO;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件附件
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bd_file_attach", resultMap = "FileAttachResultMap")
public class FileAttachPO extends BasePO {

    private static final long serialVersionUID = 5409185459234711691L;

    public static final String DB_TABLE_NAME = "bd_file_attach";

    public static final String DB_COL_TABLE_NAME = "table_name";

    public static final String DB_COL_RECORD_ID = "record_id";

    public static final String DB_COL_NAME = "name";

    public static final String DB_COL_URL = "url";

    public static final String DB_COL_ICON_URL = "icon_url";

    public static final String DB_COL_PREVIEW_URL = "preview_url";


    /**
     * 表名称
     */
    private String tableName;

    /**
     * 记录ID
     */
    private String recordId;

    /**
     * 文件原名称
     */
    private String name;

    /**
     * 文件URL
     */
    private String url;

    /**
     * 文件图标URL
     */
    private String iconUrl;

    /**
     * 文件预览URL
     */
    private String previewUrl;

}
