package com.finhub.codegen.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Data
@AllArgsConstructor
public class PathInfo {

    private String templatePath;

    private String relativeTemplatePath;

    private String templateName;

    private String targetPath;

    private String targetName;
}
