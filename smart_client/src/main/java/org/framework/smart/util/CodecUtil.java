package org.framework.smart.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLDecoder;
import java.net.URLEncoder;


/**
 * 编码与解码操作工具类
 *
 * @author rosan
 * @date: 2017/10/14 下午10:14
 * @version:1.0
 */
public final class CodecUtil {
    private static final Logger logger = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将URL编码
     *
     * @param source
     * @return
     */
    public static String encodeURL(String source) {
        String target;
        try {
            target = URLEncoder.encode(source, "utf-8");
        } catch (Exception e) {
            logger.error("encode url failed...", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 将URL解码
     *
     * @param source
     * @return
     */
    public static String decodeURL(String source) {
        String target;
        try {
            target = URLDecoder.decode(source, "utf-8");
        } catch (Exception e) {
            logger.error("decode url failed...", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
