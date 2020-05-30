package com.thread.sync;

public class StaticCounter {
    private static int count;

    public static synchronized void incr() {
        count++;
    }

    public static synchronized int getCount() {
        return count;
    }

    /**
     * 静态方法代码块 保护的是类对象
     */
    public static void incr2() {
        synchronized (StaticCounter.class) {
            count++;
        }
    }
}
/*

- 对于静态方法，synchronized保护的是类对象，这里是StaticCounter.class
- synchronized静态方法和实例方法保护的是不同的对象，不同的两个线程，可以同时执行静态方法和实例方法
 */