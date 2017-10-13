package org.framework.smart.util;

/**
 * 类型转换工具类
 *
 * @author rosan
 * @date: 2017/10/13 下午10:08
 * @version:1.0
 */
public class CastUtil {
    /**
     * 转为String类型
     *
     * @param object
     * @return
     */
    public static String castString(Object object) {
        return castString(object, "");
    }

    /**
     * 转为String类型(提供默认值)
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static String castString(Object object, String defaultValue) {
        return object != null ? String.valueOf(object) : defaultValue;
    }

    /**
     * 转换为dobule类型
     *
     * @param object
     * @return
     */
    public static double castDouble(Object object) {
        return castDobule(object, 0);
    }

    /**
     * 转换为double类型(提供默认值)
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static double castDobule(Object object, double defaultValue) {
        double doubleValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmply(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转换为Long类型
     *
     * @param object
     * @return
     */
    public static long castLong(Object object) {
        return castLong(object, 0);
    }

    /**
     * 转换为long类型(提供默认值)
     *
     * @param object
     * @param defalutValue
     * @return
     */
    public static long castLong(Object object, long defalutValue) {
        long longValue = defalutValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmply(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defalutValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转换为int类型
     *
     * @param object
     * @return
     */
    public static int castInt(Object object) {
        return castInt(object, 0);
    }

    /**
     * 转换成int类型(提供默认值)
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static int castInt(Object object, int defaultValue) {
        int intValue = defaultValue;
        if (object != null) {
            String strValue = castString(object);
            if (StringUtil.isNotEmply(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转换类型为boolean类型
     *
     * @param object
     * @return
     */
    public static boolean castBoolean(Object object) {
        return castBoolean(object, false);
    }

    /**
     * 转换为boolean类型(带默认参数)
     *
     * @param object
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object object, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (object != null) {
            booleanValue = Boolean.parseBoolean(castString(object));
        }
        return booleanValue;
    }
}
