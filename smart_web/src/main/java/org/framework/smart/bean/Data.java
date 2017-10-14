package org.framework.smart.bean;

/**
 * 返回数据对象
 *
 * @author rosan
 * @date: 2017/10/14 下午10:08
 * @version:1.0
 */
public class Data {
    /**
     * 数据模型
     */
    private Object model;

    public Data(Object model) {
        this.model = model;
    }

    public Object getModel() {
        return model;
    }
}
