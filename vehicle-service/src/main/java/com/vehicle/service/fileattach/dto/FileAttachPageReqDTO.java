package com.vehicle.service.fileattach.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件附件 分页 ReqDTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileAttachPageReqDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

    /**
     * 主键
     */
    private String id;

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

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 创建人ID
     */
    private String createBy;

    /**
     * 创建人名称
     */
    private String createName;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 更新人ID
     */
    private String updateBy;

    /**
     * 更新人名称
     */
    private String updateName;

    /**
     * 是否删除
     */
    private Integer isDel;

    /**
     * 是否测试
     */
    private Integer isTest;
}
