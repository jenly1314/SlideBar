package com.king.slidebar.util;

import android.text.TextUtils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;

import java.util.regex.Pattern;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/31
 */

public final class PinyinUtil {

    private static final HanyuPinyinOutputFormat hpoformat = new HanyuPinyinOutputFormat();

    public static String chineneToSpell(String chineseStr){
        try {
            return PinyinHelper.toHanyuPinyinString(chineseStr, hpoformat,"");
        } catch (Exception e) {

        }
        return chineseStr;
    }

    /**
     * 处理首字母
     *
     * @return 字符串的首字母，不是A~Z范围的返回#
     */
    public static String formatAlpha(String string) {

        String str = chineneToSpell(string);

        if(TextUtils.isEmpty(str)){
            return "#";
        }

        char c = str.substring(0, 1).charAt(0);
        Pattern pattern = Pattern.compile("^[A-Za-z]+$");
        if (pattern.matcher(String.valueOf(c)).matches()) {
            return (String.valueOf(c)).toUpperCase();
        } else {
            return "#";
        }
    }
}
