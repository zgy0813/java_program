package com.thread.sync;

public class Counter {
    private int count;
    public synchronized void incr(){
        count ++;
    }
    public synchronized int getCount() {
        return count;
    }

    /**
     * 实例方法代码块 保护的是this
     */
    public void incr2(){
        synchronized (this) {
            count ++;
        }
    }
}
// incr和getCount方法加上synchronized修饰，方法内的代码就变成了原子操作，当多个线程并发更新同一个Counter对象的时候，也不会出现问题。