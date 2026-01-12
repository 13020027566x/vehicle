package com.finhub.framework.i18n.component;

import com.finhub.framework.i18n.constant.I18nConstants;
import com.finhub.framework.i18n.property.I18nProperties;

import cn.hutool.core.util.StrUtil;
import static com.finhub.framework.i18n.constant.I18nConstants.I18N_ATTRIBUTE;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;


/**
 * 获取国际化文档 MessageSource
 * 支持自动刷新，及 父子（不同产品线）国际化
 *
 * @author Mickey
 * @version 1.0.0
 * @since 2020/2/6 下午3:05
 */
@Data
@Slf4j
@EqualsAndHashCode(callSuper = false)
public class CustomReloadableResourceBundleMessageSource extends ReloadableResourceBundleMessageSource {

    private I18nProperties i18nProperties;

    @PostConstruct
    public void init() {
        log.info("init CustomResourceBundleMessageSource begin.");
        if (StrUtil.isNotBlank(i18nProperties.getBaseFolder())) {
            try {
                this.setBasenames(getAllBaseNames(i18nProperties.getBaseFolder()));
            } catch (IOException e) {
                log.error(StrUtil.format("init MessageResourceExtension fail. [baseFolder={}]", i18nProperties.getBaseFolder()), e);
            }
        }

        // 设置父 MessageSource
        ResourceBundleMessageSource parent = new ResourceBundleMessageSource();
        parent.setBasename(i18nProperties.getBaseFolder() + I18nConstants.S_SLASH + i18nProperties.getBaseName());
        parent.setDefaultEncoding(i18nProperties.getDefaultEncoding());
        parent.setUseCodeAsDefaultMessage(i18nProperties.isUseCodeAsDefaultMessage());
        parent.setCacheSeconds(i18nProperties.getCacheSeconds());
        this.setParentMessageSource(parent);
    }

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String currentBasename = parseCurrentBasename();
        if (StrUtil.isBlank(currentBasename)) {
            return super.resolveCodeWithoutArguments(code, locale);
        }

        if (getCacheMillis() < 0) {
            PropertiesHolder propHolder = getMergedProperties(locale);
            String result = propHolder.getProperty(code);
            if (result != null) {
                return result;
            }
        } else {
            if (StrUtil.isNotBlank(currentBasename)) {
                List<String> filenames = calculateAllFilenames(currentBasename, locale);
                for (String filename : filenames) {
                    PropertiesHolder propHolder = getProperties(filename);
                    String result = propHolder.getProperty(code);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Resolves the given message code as key in the retrieved bundle files,
     * using a cached MessageFormat instance per message code.
     */
    @Override
    @Nullable
    protected MessageFormat resolveCode(String code, Locale locale) {
        String currentBasename = parseCurrentBasename();
        if (StrUtil.isBlank(currentBasename)) {
            return super.resolveCode(code, locale);
        }

        if (getCacheMillis() < 0) {
            PropertiesHolder propHolder = getMergedProperties(locale);
            MessageFormat result = propHolder.getMessageFormat(code, locale);
            if (result != null) {
                return result;
            }
        } else {
            if (StrUtil.isNotBlank(currentBasename)) {
                List<String> filenames = calculateAllFilenames(currentBasename, locale);
                for (String filename : filenames) {
                    PropertiesHolder propHolder = getProperties(filename);
                    MessageFormat result = propHolder.getMessageFormat(code, locale);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }

    private String parseCurrentBasename() {
        // 获取 request 中设置的指定国际化文件名
        ServletRequestAttributes attr;
        try {
            attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        } catch (IllegalStateException e) {
            return null;
        }

        final String i18nFile = (String) attr.getAttribute(I18N_ATTRIBUTE, RequestAttributes.SCOPE_REQUEST);
        if (StrUtil.isNotBlank(i18nFile)) {
            // 获取在basenameSet中匹配的国际化文件名
            return getBasenameSet().stream()
                .filter(name -> StringUtils.endsWithIgnoreCase(name, i18nFile))
                .findFirst().orElse(null);
        }
        return null;
    }

    /**
     * 获取文件夹下所有的国际化文件名
     *
     * @param folderName 文件名
     * @return
     * @throws java.io.IOException
     */
    private String[] getAllBaseNames(final String folderName) throws IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(folderName);
        if (null == url) {
            log.warn("Not Found Resource Path. [folderName={}]", folderName);
            return new String[0];
        }

        List<String> baseNames = new ArrayList<>();
        if (url.getProtocol().equalsIgnoreCase(I18nConstants.S_FILE)) {
            // 文件夹形式,用File获取资源路径
            File file = new File(url.getFile());
            if (file.exists() && file.isDirectory()) {
                baseNames = Files.walk(file.toPath())
                    .filter(path -> path.toFile().isFile())
                    .map(Path::toString)
                    .map(path -> ResourceUtils.CLASSPATH_URL_PREFIX + path.substring(path.lastIndexOf(folderName)))
                    .map(this::getI18FileName)
                    .distinct()
                    .collect(Collectors.toList());
            } else {
                log.error("指定的 baseFile 不存在或者不是文件夹");
            }
        } else if (url.getProtocol().equalsIgnoreCase(I18nConstants.S_JAR)) {
            // jar 包形式，用 JarEntry 获取资源路径
            String jarPath = url.getFile().substring(url.getFile().indexOf(I18nConstants.S_COLON) + 1,
                url.getFile().indexOf(I18nConstants.S_EXCLAMATORY));
            JarFile jarFile = new JarFile(new File(jarPath));
            List<String> baseJars = jarFile.stream()
                .map(ZipEntry::toString)
                .filter(jar -> jar.endsWith(folderName + I18nConstants.S_SLASH)).collect(Collectors.toList());
            if (baseJars.isEmpty()) {
                log.info("不存在的资源文件夹. [folderName={}]", folderName);
                return new String[0];
            }

            baseNames = jarFile.stream().map(ZipEntry::toString)
                .filter(jar -> baseJars.stream().anyMatch(jar::startsWith))
                .filter(jar -> jar.endsWith(".properties"))
                .map(jar -> ResourceUtils.CLASSPATH_URL_PREFIX + jar.substring(jar.lastIndexOf(folderName)))
                .map(this::getI18FileName)
                .distinct()
                .collect(Collectors.toList());

        }
        return baseNames.toArray(new String[0]);
    }

    /**
     * 把普通文件名转换成国际化文件名
     *
     * @param filename
     * @return
     */
    private String getI18FileName(String filename) {
        filename = filename.replace(".properties", I18nConstants.S_EMPTY);
        for (int i = 0; i < 2; i++) {
            int index = filename.lastIndexOf(I18nConstants.S_UNDERLINE);
            if (index != -1) {
                filename = filename.substring(0, index);
            }
        }
        return filename.replace(I18nConstants.S_BACKSLASH, I18nConstants.S_SLASH);
    }
}
