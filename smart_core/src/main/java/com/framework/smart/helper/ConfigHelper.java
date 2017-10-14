package com.framework.smart.helper;

import com.framework.smart.ConfigConstant;
import org.framework.smart.util.PropsUtil;

import java.util.Properties;

/**
 * 属性操作类
 *
 * @author rosan
 * @date: 2017/10/13 下午10:08
 * @version:1.0
 */
public class ConfigHelper {
    private static final Properties CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    /**
     * 获取Jdbc驱动
     *
     * @return
     */
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    /**
     * 获取Jdbc URL
     *
     * @return
     */
    public static String getJdbcUrl() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    /**
     * 获取Jdbc 登录用户名
     *
     * @return
     */
    public static String getJdbcUserName() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    /**
     * 获取Jdbc登录密码
     *
     * @return
     */
    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    /**
     * 获取应用基础包名
     *
     * @return
     */
    public static String getAppBasePackage() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    /**
     * 获取应用视图基础路径
     *
     * @return
     */
    public static String getAppViewPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_VIEW_PATH, "/WEB-INF/view/");
    }

    /**
     * 获取静态自愿基础路径
     *
     * @return
     */
    public static String getAppAsetPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSET_PATH, "/asset/");
    }
}

