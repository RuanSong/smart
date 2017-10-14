package org.framework.smart.util;

import org.apache.commons.lang3.ArrayUtils;

/**
 * 数据工具类
 *
 * @author rosan
 * @date: 2017/10/14 下午9:28
 * @version:1.0
 */
public class ArrayUtil {
    /**
     * 判断数组是否为非空
     *
     * @param array
     * @return
     */
    public static boolean isNotEmpty(Object[] array) {
        return !isEmpty(array);
    }

    /**
     * 判断数组是否为空
     *
     * @param array
     * @return
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }
}

