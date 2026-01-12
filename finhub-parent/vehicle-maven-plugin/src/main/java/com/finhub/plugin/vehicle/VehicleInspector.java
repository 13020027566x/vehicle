package com.finhub.plugin.vehicle;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午4:19
 */
@Mojo(name = "inspect", defaultPhase = LifecyclePhase.COMPILE)
public class VehicleInspector extends AbstractMojo {

    @Parameter(property = "inspect.message", defaultValue = "Hello World")
    private String message;

    /**
     * 项目的主源码目录，默认为 "src/main/java/"
     */
    @Parameter(property = "project.build.sourceDirectory", required = true, readonly = true)
    private File sourceDirectory;

    /**
     * 项目的测试源码目录，默认为 "/src/test/java/"
     */
    @Parameter(property = "project.build.testSourceDirectory", required = true, readonly = true)
    private File testSourceDirectory;

    /**
     * 项目构建输出目录，默认为 "target/"
     */
    @Parameter(property = "project.build.directory", required = true, readonly = true)
    private File directory;

    /**
     * 项目主代码编译输出目录，默认为 "target/classes/"
     */
    @Parameter(property = "project.build.outputDirectory", required = true, readonly = true)
    private File outputDirectory;

    /**
     * 项目测试代码编译输出目录，默认为 "target/testclasses/"
     */
    @Parameter(property = "project.build.testOutputDirectory", required = true, readonly = true)
    private File testOutputDirectory;

    /**
     * 项目根目录
     */
    @Parameter(property = "project.basedir", required = true, readonly = true)
    private File basedir;

    /**
     * 项目 groupId
     */
    @Parameter(property = "project.groupId", required = true, readonly = true)
    private String groupId;

    /**
     * 项目 artifactId
     */
    @Parameter(property = "project.artifactId", required = true, readonly = true)
    private String artifactId;

    /**
     * 项目 version,与 ${version} 等价
     */
    @Parameter(property = "project.version", required = true, readonly = true)
    private String version;

    /**
     * 项目打包输出文件的名称，默认为 "${project.artifactId}${project.version}"
     */
    @Parameter(property = "project.build.finalName", required = true, readonly = true)
    private String finalName;

    /**
     * 执行
     *
     * @throws MojoExecutionException 会显示 "Build Error" 错误信息，表示未预期的错误
     * @throws MojoFailureException   会显示 "Build Failure" 错误信息，表示可预期的错误
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(message);

        getLog().info("sourceDirectory ===> " + sourceDirectory.getAbsolutePath());
        getLog().info("testSourceDirectory ===> " + testSourceDirectory.getAbsolutePath());
        getLog().info("directory ===> " + directory.getAbsolutePath());
        getLog().info("outputDirectory ===> " + outputDirectory.getAbsolutePath());
        getLog().info("testOutputDirectory ===> " + testOutputDirectory.getAbsolutePath());
        getLog().info("basedir ===> " + basedir.getAbsolutePath());
        getLog().info("groupId ===> " + groupId);
        getLog().info("artifactId ===> " + artifactId);
        getLog().info("version ===> " + version);
        getLog().info("finalName ===> " + finalName);
    }
}
