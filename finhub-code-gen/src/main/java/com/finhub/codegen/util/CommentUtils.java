package com.finhub.codegen.util;

import com.finhub.framework.core.str.StrConstants;

/**
 * @author Mickey
 * @version 1.0
 * @since 2015/05/13 17:34
 */
public class CommentUtils {

    /**
     * 返回注释信息
     *
     * @param all
     * @return
     */
    public static String parse(String all) {
        String comment;
        int index = all.indexOf("COMMENT='");
        if (index < 0) {
            return StrConstants.S_EMPTY;
        }
        comment = all.substring(index + 9);
        comment = comment.substring(0, comment.length() - 1);
        return comment;
    }
}
