package com.finhub.framework.core.safe;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.str.StrConstants;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.util.HtmlUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Web防火墙工具类
 *
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
@Slf4j
@UtilityClass
public class WafUtils {

    /**
     * 标准http头对象属性
     */
    public static final Set<String> STANDARD_HTTP_HEADERS =
        Arrays.stream(Header.values()).map(header -> header.getValue().toLowerCase()).collect(Collectors.toSet());

    private static final Pattern SCRIPT_PATTERN = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);

    private static final Pattern SCRIPT_SRC_SINGLE_PATTERN = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE
        | Pattern.MULTILINE | Pattern.DOTALL);

    private static final Pattern SCRIPT_SRC_DOUBLE_PATTERN = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
        | Pattern.MULTILINE | Pattern.DOTALL);

    private static final Pattern SCRIPT_END_PATTERN = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);

    private static final Pattern SCRIPT_START_PATTERN = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE
        | Pattern.MULTILINE | Pattern.DOTALL);

    private static final Pattern SCRIPT_EVAL_PATTERN = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE
        | Pattern.MULTILINE | Pattern.DOTALL);

    private static final Pattern SCRIPT_EXPRESSION_PATTERN = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE
        | Pattern.MULTILINE | Pattern.DOTALL);

    private static final Pattern JAVA_SCRIPT_PATTERN = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);

    private static final Pattern VB_SCRIPT_PATTERN = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);

    private static final Pattern ONLOAD_SCRIPT_PATTERN = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE
        | Pattern.MULTILINE | Pattern.DOTALL);

    private static final Pattern ONERROR_SCRIPT_PATTERN = Pattern.compile("onerror(.*?)=", Pattern.CASE_INSENSITIVE
        | Pattern.MULTILINE | Pattern.DOTALL);

    private static final Pattern SQL_INJECTION =
        Pattern.compile("(')|(--)|(%27)|(%7C)|(/\\*)|(\\*/)", Pattern.CASE_INSENSITIVE);

    private static final List<Pattern> XSS_PATTERN_LIST = Lists.newArrayList(Pattern.compile("<(no)?script[^>]*>.*?</(no)?script>", Pattern.CASE_INSENSITIVE),
        Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("(javascript:|vbscript:|view-source:)*", Pattern.CASE_INSENSITIVE),
        Pattern.compile("<(\"[^\"]*\"|\'[^\']*\'|[^\'\">])*>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("(window\\.location|window\\.|\\.location|document\\.cookie|document\\.|alert\\(.*?\\)|window\\.open\\()*", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL),
        Pattern.compile("<+\\s*\\w*\\s*(oncontrolselect|oncopy|oncut|ondataavailable|ondatasetchanged|ondatasetcomplete|ondblclick|ondeactivate|ondrag|ondragend|ondragenter|ondragleave|ondragover|ondragstart|ondrop|onerror=|onerroupdate|onfilterchange|onfinish|onfocus|onfocusin|onfocusout|onhelp|onkeydown|onkeypress|onkeyup|onlayoutcomplete|onload|onlosecapture|onmousedown|onmouseenter|onmouseleave|onmousemove|onmousout|onmouseover|onmouseup|onmousewheel|onmove|onmoveend|onmovestart|onabort|onactivate|onafterprint|onafterupdate|onbefore|onbeforeactivate|onbeforecopy|onbeforecut|onbeforedeactivate|onbeforeeditocus|onbeforepaste|onbeforeprint|onbeforeunload|onbeforeupdate|onblur|onbounce|oncellchange|onchange|onclick|oncontextmenu|onpaste|onpropertychange|onreadystatechange|onreset|onresize|onresizend|onresizestart|onrowenter|onrowexit|onrowsdelete|onrowsinserted|onscroll|onselect|onselectionchange|onselectstart|onstart|onstop|onsubmit|onunload)+\\s*=+", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL));

    /**
     * 过滤XSS和SQL
     *
     * @param text
     * @param filterXSS
     * @param filterSQL
     * @return
     */
    public static String filterText(final String text, final boolean filterXSS, final boolean filterSQL) {
        String filterText = StrUtil.trim(text);

        if (JSONUtil.isJson(filterText)) {
            return filterText;
        }

        if (filterXSS) {
            filterText = cleanXSS(filterText);
        }

        if (filterSQL) {
            filterText = stripSqlInjection(filterText);
        }

        if (filterXSS || filterSQL) {
            filterText = HtmlUtils.htmlEscape(filterText);
        }
        return filterText;
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤XSS脚本内容
     */
    public static String cleanXSS(String value) {
        if (StrUtil.isNotBlank(value)) {
            Matcher matcher;
            for (Pattern pattern : XSS_PATTERN_LIST) {
                matcher = pattern.matcher(value);
                if (matcher.find()) {
                    value = matcher.replaceAll(StrConstants.S_EMPTY);
                }
            }
            value = value.replaceAll("<", "<").replaceAll(">", ">");
        }
        return value;
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤XSS脚本内容
     */
    public static String stripXSS(String value) {
        String rlt = null;

        if (Func.isNotEmpty(value)) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            rlt = value.replaceAll(StrConstants.S_EMPTY, StrConstants.S_EMPTY);

            // Avoid anything between script tags
            rlt = SCRIPT_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Avoid anything in a src='...' type of expression
            rlt = SCRIPT_SRC_SINGLE_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            rlt = SCRIPT_SRC_DOUBLE_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Remove any lonesome </script> tag
            rlt = SCRIPT_END_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Remove any lonesome <script ...> tag
            rlt = SCRIPT_START_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Avoid eval(...) expressions
            rlt = SCRIPT_EVAL_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Avoid expression(...) expressions
            rlt = SCRIPT_EXPRESSION_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Avoid javascript:... expressions
            rlt = JAVA_SCRIPT_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Avoid vbscript:... expressions
            rlt = VB_SCRIPT_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Avoid onload= expressions
            rlt = ONLOAD_SCRIPT_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);

            // Avoid onerror= expressions
            rlt = ONERROR_SCRIPT_PATTERN.matcher(rlt).replaceAll(StrConstants.S_EMPTY);
        }

        return rlt;
    }

    public static boolean validateXSS(String value) {
        if (value != null && !value.isEmpty()) {
            Matcher matcher;
            for (Pattern pattern : XSS_PATTERN_LIST) {
                matcher = pattern.matcher(value);
                if (matcher.find()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @param value 待处理内容
     * @return
     * @Description 过滤SQL注入内容
     */
    public static String stripSqlInjection(String value) {
        return (value == null || value.isEmpty()) ? value : value.replaceAll("('.+--)|(--)|(%7C)|(/\\*)|(\\*/)", StrConstants.S_EMPTY);
    }
}
