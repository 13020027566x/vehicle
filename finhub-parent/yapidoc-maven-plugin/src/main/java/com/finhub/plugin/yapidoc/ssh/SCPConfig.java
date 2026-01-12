package com.finhub.plugin.yapidoc.ssh;

import lombok.Data;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * @author fenglibin
 * @version V1.0
 */
@Data
public class SCPConfig {
    /**
     * 目录
     */
    @Parameter(property = "remoteTargetDirectory", required = true)
    private String remoteTargetDirectory;

    /**
     * 权限
     */
    @Parameter(property = "model")
    private String model;
}
