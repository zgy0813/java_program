package com.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 共享内存
 */
public class ShareMemoryDemo {
    private static int shared = 0;
    private static void incrShared() {
        shared ++;
    }

    // 静态内部类
    static class ChildThread extends Thread {
        List<String> list;
        public ChildThread(List<String> list) {
            this.list = list;
        }
        @Override
        public void run() {
            incrShared();
            list.add(Thread.currentThread().getName());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> list = new ArrayList<>();
        Thread t1 = new ChildThread(list);
        Thread t2 = new ChildThread(list);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("shared: " + shared);
        System.out.println("list: " + list);
    }
}

/*

- 该例子中有三条执行流，一条执行main方法，两条执行ChildThread的run方法
- 不同执行流可以访问和操作相同的变量，如本例中的shared和list变量
- 不同执行流可以执行相同的程序代码，如本例中的incrShared()方法，childThread中的run方法；==在分析代码执行过程时，理解代码在被哪个线程执行是非常重要的==
- 当多条执行流执行相同代码的时候，每条执行流都有单独的栈，方法中的参数和局部变量都有自己的一份

当多条执行流可以操作相同的程序代码时，可能会出现一些意料之外的情况，包括竞争条件和内存可见性问题=

 */