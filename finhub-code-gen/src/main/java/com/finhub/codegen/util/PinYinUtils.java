package com.finhub.codegen.util;

import com.finhub.framework.core.Func;
import com.finhub.framework.core.charset.CharsetUtils;
import com.finhub.framework.core.str.StrConstants;
import com.finhub.framework.exception.UtilException;

import cn.hutool.core.util.StrUtil;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mickey
 * @version 1.0
 * @since 2014/9/22 14:33
 */
@Slf4j
@UtilityClass
public class PinYinUtils {

    public static final String STR_C = "c";
    public static final String STR_Z = "z";
    public static final String STR_S = "s";
    public static final char CHAR_C = 'h';
    public static final String STR_H = "h";

    /**
     * 取得拼音首字母
     */
    public static String getPinYininitial(String src) {

        if (StrUtil.isBlank(src)) {
            return StrConstants.S_EMPTY;
        }

        HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();
        hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);

        char[] input = src.trim().toCharArray();
        input = ArrayUtils.subarray(input, 0, 1);
        StringBuffer output = new StringBuffer();

        try {
            for (int i = 0; i < input.length; i++) {
                if (Character.toString(input[i]).matches("[\u4E00-\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], hanYuPinOutputFormat);
                    output.append(temp[0]);
                    output.append(" ");
                } else {
                    output.append(Character.toString(input[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new UtilException(e);
        }

        String result = StrUtil.trim(output.toString()).toUpperCase();

        return String.valueOf(result.charAt(0));
    }


    private static HanyuPinyinOutputFormat getDefaultOutputFormat() {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        // 小写
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 没有音调数字
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // v显示
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        return format;
    }

    private static List<String[]> doExchange(String[][] strJaggedArray) {
        int len = strJaggedArray.length;
        List<String[]> list = new ArrayList<String[]>();
        list.add(new String[]{StrConstants.S_EMPTY, StrConstants.S_EMPTY});
        for (int x = 0; x < len; x++) {
            int len1 = list.size();
            String[] pinyins = strJaggedArray[x];
            List<String[]> tmpList = new ArrayList<String[]>();
            for (int i = 0; i < pinyins.length; i++) {
                String wholeSpell = pinyins[i];
                if (StrUtil.isBlank(wholeSpell)) {
                    continue;
                }
                String firstSpell = Character.toString(wholeSpell.charAt(0));
                boolean isFlag = (
                    StrUtil.equals(STR_C, firstSpell) || StrUtil.equals(STR_Z, firstSpell) || StrUtil.equals(STR_S, firstSpell)) && Func.isNotEmpty(wholeSpell);
                if (isFlag) {
                    char secondChar = wholeSpell.charAt(1);
                    if (secondChar == CHAR_C) {
                        firstSpell += STR_H;
                    }
                }

                for (int j = 0; j < len1; j++) {
                    String[] tmp = list.get(j);
                    String newWholeSpell = tmp[0] + wholeSpell;
                    String newFirstSpell = tmp[1] + firstSpell;
                    tmpList.add(new String[]{newWholeSpell, newFirstSpell});
                }
            }
            if (tmpList.size() != 0) {
                list.clear();
                list.addAll(tmpList);
            }
        }
        return list;
    }

    public static boolean isChinese(char c) {
        return isChinese(Character.toString(c));
    }

    public static boolean isChinese(String s) {
        return s.matches("[\\u4E00-\\u9FA5]+");
    }

    public static String getPinYin(String src) {
        char[] t1 = src.toCharArray();
        String[] t2;

        HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
        t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        t3.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        t3.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuilder t4 = new StringBuilder();
        int t0 = t1.length;
        try {
            for (int i = 0; i < t0; i++) {
                if (Character.toString(t1[i]).matches("[\\一-\\龥]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
                    // 取出该汉字全拼的第一种读音并连接到字符串t4后
                    t4.append(t2[0]);
                } else {
                    t4.append(Character.toString(t1[i]));
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            throw new UtilException(e);
        }
        return t4.toString();
    }

    public static String getPinYinHeadChar(String str) {
        StringBuilder convert = new StringBuilder();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert.append(pinyinArray[0].charAt(0));
            } else {
                convert.append(word);
            }
        }
        return convert.toString();
    }

    public static String getCnASCII(String cnStr) {
        StringBuilder strBuf = new StringBuilder();
        byte[] bGBK = cnStr.getBytes(CharsetUtils.DEFAULT_CHARSET);
        for (byte aBGBK : bGBK) {
            strBuf.append(Integer.toHexString(aBGBK & 0xff));
        }
        return strBuf.toString();
    }
}
