package org.framework.smart.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 *
 * @author rosan
 * @date: 2017/10/13 下午10:01
 * @version:1.0
 */
public class StringUtil {
    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断师傅穿是否为非空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmply(String str) {
        return !isEmpty(str);
    }

    /**
     * 根据特定分隔符拆分字符串
     *
     * @param source
     * @param split
     * @return
     */
    public static String[] splitString(String source, String split) {
        String[] splitArray = new String[]{};
        if (source != null && split != null) {
            splitArray = source.split(split);
        }
        return splitArray;
    }
}
