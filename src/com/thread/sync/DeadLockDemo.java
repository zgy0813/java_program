package com.thread.sync;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 死锁demo
 */
public class DeadLockDemo {
    private static Object lockA = new Object();
    private static Object lockB = new Object();
    private static void startThreadA() {
        Thread aThread = new Thread() {
            @Override
            public void run() {
                synchronized (lockA) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    synchronized (lockB) { }
                }
            }
        };
        aThread.start();
    }
    private static void startThreadB() {
        Thread bThread = new Thread() {
            @Override
            public void run() {
                synchronized (lockB) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    synchronized (lockA) { }
                }
            }
        };
        bThread.start();
    }

    public static void main(String[] args) {
        startThreadA();
        startThreadB();
    }
}
/*
使用synchronized或者其他锁，要注意死锁，比如a、b两个线程，a持有锁A，在等待锁B，而b持有锁B,在等待锁A。a和b就陷入了互相等待，最后谁都在执行不下去。
 */