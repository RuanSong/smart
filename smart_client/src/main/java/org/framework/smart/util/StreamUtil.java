package org.framework.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 流操作工具类
 *
 * @author rosan
 * @date: 2017/10/14 下午10:10
 * @version:1.0
 */
public class StreamUtil {
    private static final Logger logger = LoggerFactory.getLogger(StreamUtil.class);

    public static String getString(InputStream is) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedInputStream = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedInputStream.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            logger.error("get string failed ...", e);
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
