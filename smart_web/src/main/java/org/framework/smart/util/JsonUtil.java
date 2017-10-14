package org.framework.smart.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JSON工具类
 *
 * @author rosan
 * @date: 2017/10/14 下午10:18
 * @version:1.0
 */
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将POJO转为JSON
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String toJson(T object) {
        String json;
        try {
            json = OBJECT_MAPPER.writeValueAsString(object);
        } catch (Exception e) {
            logger.error("convert POJO to JSON failed...", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将JSON转为POJO
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T formJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            logger.error("convert JSON to POJO failed...", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }
}
