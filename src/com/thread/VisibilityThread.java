package com.thread;

/**
 * 内存可见性
 */
public class VisibilityThread{
    private static boolean shutdown = false;

    static class HelloThread extends Thread {
        @Override
        public void run() {
            while(!shutdown) {
                // do nothing
            }
            System.out.println("exit hello");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloThread().start();
        Thread.sleep(1000);
        shutdown = true;
        System.out.println("exit main");
    }
}
/*
有一个共享的boolean变量shutdown，起初为false，HelloThread在shutdown不为true的时候一直死循环，当shutdown为true时输出"exit hello"，main线程启动HelloThread后休息了一会，然后设置shutdown为true,最后输出"exit main"。
期望的结果两个线程都退出，但是HelloThread永远都不会退出，也就是对于HelloThread执行流来说shutdown永远为false，即使main线程已经更改为了true。这就是内存可见性问题。

在计算机系统中，除了内存、数据还会被缓存到CPU的寄存器以及各级缓存中，当访问一个变量时，可能直接从寄存器或CPU缓存中获取，而不一定到内存中去。一个线程对内存的修改，另一个线程看不到，一是修改没有及时同步到内存，二是线程根本没从内存读
 */