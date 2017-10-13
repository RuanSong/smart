package org.framework.smart.helper;

import org.framework.smart.ConfigConstant;
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
}

