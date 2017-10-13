package org.framework.smart.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 *
 * @author rosan
 * @date: 2017/10/13 下午10:04
 * @version:1.0
 */
public class CollectionUtil {
    /**
     * 判断Collection是否为空
     *
     * @param collection
     * @return
     */
    public static boolean isEmply(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断Collection是否为非空
     *
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !CollectionUtils.isEmpty(collection);
    }

    /**
     * 判断Map是否为空
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断map是否为非空
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
}
