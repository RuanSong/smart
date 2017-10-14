package org.framework.smart.bean;

import org.framework.smart.util.CastUtil;

import java.util.Map;

/**
 * 请求参数对象
 *
 * @author rosan
 * @date: 2017/10/14 下午10:03
 * @version:1.0
 */
public class Param {
    private Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 根据参数名获取long型参数址
     *
     * @param name
     * @return
     */
    public long getLong(String name) {
        return CastUtil.castLong(paramMap.get(name));
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
