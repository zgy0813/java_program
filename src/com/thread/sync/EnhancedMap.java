package com.thread.sync;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

public class EnhancedMap<K, V> {
    Map<K, V> map;

    public EnhancedMap(Map<K, V> map) {
        this.map = Collections.synchronizedMap(map);
    }

    /**
     * 不能实现线程安全
     */
    public V putIfAbsent(K key, V value) {
        V old = map.get(key);
        if (old != null) {
            return old;
        }
        return map.put(key, value);
    }

    /**
     * 不能实现线程安全，不同的对象
     */
    public synchronized V putIfAbsent2(K key, V value) {
        V old = map.get(key);
        if (old != null) {
            return old;
        }
        return map.put(key, value);
    }

    /**
     * 实现线程安全 使用map对象锁
     */
    public V putIfAbsent3(K key, V value) {
        synchronized (map) {
            V old = map.get(key);
            if (old != null) {
                return old;
            }
            return map.put(key, value);
        }
    }

    public V put(K key, V value) {
        return map.put(key, value);
    }
}

/*
- EnhancedMap是一个修饰类，接受一个Map对象，调用synchronizedMap转换为了同步对象map，增加一个方法putIfAbsent，该方法只有在原Map中没有对应键的时候才添加
- map的每一个方法都是安全的，但这个复合方法putIfAbsent却不是的，可能多个线程都执行完了检查第一步，都发现Map中没有对应的键，然后调用put，这就破坏了putIfAbsent方法期望保持的语义
 */

/*
- 加上synchronized就能实现安全吗，答案是否定的，因为同不错对象了
- putIfAbsent同步使用的是EnhancedMap对象，而其他方法（如代码中的put方法）使用的是Collections.synchronizedMap返回的对象map，两者是不同的对象
- 要解决这个问题，所有方法必须使用相同的锁，可以使用EnhancedMap也可以使用map
- 使用EnhancedMap对象作为锁，则EnhancedMap中的所有方法都需要加上synchronized
- 使用map对象作为锁，putIfAbsent可以修改为如下
 */