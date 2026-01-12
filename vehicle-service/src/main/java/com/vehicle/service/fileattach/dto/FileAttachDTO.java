package com.vehicle.service.fileattach.dto;

import com.finhub.framework.common.dto.BaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件附件 DTO
 *
 * @author Mickey
 * @version 1.0
 * @since 2019/06/20 17:27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileAttachDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 5409185459234711691L;

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
