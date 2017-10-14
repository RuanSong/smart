package org.framework.smart.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 属性工具类
 *
 * @author rosan
 * @date: 2017/10/13 下午10:04
 * @version:1.0
 */
public final class PropsUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载配置文件
     *
     * @param fileName
     * @return
     */
    public static Properties loadProps(String fileName) {
        Properties properties = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if (is == null) {
                throw new FileNotFoundException(fileName + " file is not found");
            }
            properties = new Properties();
            properties.load(is);

        } catch (Exception e) {
            logger.error("load properties file fail ", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(" close input stream fail ", e);
                }
            }
        }
        return properties;
    }

    /**
     * 获取字符串类型属性
     *
     * @param properties
     * @param key
     * @return
     */
    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    /**
     * 获取字符串类型属性(指定默认值)
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;
        if (properties.contains(key)) {
            value = properties.getProperty(key);
        }
        return value;
    }

    /**
     * 获取int类型属性
     *
     * @param properties
     * @param key
     * @return
     */
    public static int getInt(Properties properties, String key) {
        return getInt(properties, key, 0);
    }

    /**
     * 获取int类型属性(指定默认值)
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(Properties properties, String key, int defaultValue) {
        int value = defaultValue;
        if (properties.contains(key)) {
            value = CastUtil.castInt(properties.getProperty(key), defaultValue);
        }
        return value;
    }

    /**
     * 获取boolean类型属性
     *
     * @param properties
     * @param key
     * @return
     */
    public static boolean getBoolean(Properties properties, String key) {
        return getBoolean(properties, key, false);
    }

    /**
     * 获取boolean类型属性(指定默认值)
     *
     * @param properties
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(Properties properties, String key, boolean defaultValue) {
        boolean value = defaultValue;
        if (properties.contains(key)) {
            value = CastUtil.castBoolean(properties.getProperty(key), defaultValue);
        }
        return value;
    }
}
