package com.thread.sync;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class IteratorSyncDemo {
    private static void startModifyThread(final List<String> list) {
        Thread modifyThread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    list.add("item " +i);
                    try {
                        Thread.sleep((int) (Math.random() * 10));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        modifyThread.start();
    }

    /**
     * 会抛出异常
     */
    private static void startIteratorThread(final List<String> list) {
        Thread iteratorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    for (String str : list) {
                        System.out.println(str);
                    }
                }
            }
        });
        iteratorThread.start();
    }

    /**
     * 给遍历对象加上synchronized，解决遍历不同步的问题
     */
    private static void startIteratorThread2(final List<String> list) {
        Thread iteratorThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (list) {
                        for (String str : list) {
                            System.out.println(str);
                        }
                    }
                }
            }
        });
        iteratorThread.start();
    }

    public static void main(String[] args) {
        final List<String> list = Collections.synchronizedList(new ArrayList<>());
        startIteratorThread2(list);
        startModifyThread(list);
    }
}
