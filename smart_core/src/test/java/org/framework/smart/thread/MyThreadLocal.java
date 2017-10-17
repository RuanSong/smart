package org.framework.smart.thread;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rosan
 * @date: 2017/10/16 下午10:53
 * @version:1.0
 */
public class MyThreadLocal<T> {
    private Map<Thread, T> container = Collections.synchronizedMap(new HashMap<Thread, T>());

    public void set(T value) {
        container.put(Thread.currentThread(), value);
    }

    public T get() {
        Thread thread = Thread.currentThread();
        T value = container.get(Thread.currentThread());
        if (value == null && !container.containsKey(thread)) {
            value = initialValue();
            container.put(thread, value);
        }
        return value;
    }

    public void remove() {
        container.remove(Thread.currentThread());
    }

    protected T initialValue() {
        return null;
    }
}
