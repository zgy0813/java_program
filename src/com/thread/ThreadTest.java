package com.thread;

/**
 * 继承Thread并重写run方法实现一个线程
 */
public class ThreadTest extends Thread{
    @Override
    public void run() {
        // 每个Thread都有一个id和name，可以判断在哪个线程中执行的
        System.out.println("thread id: " + Thread.currentThread().getId());
        System.out.println("thread name: " + Thread.currentThread().getName());
    }

    /**
     * 启动一个线程，创建线程对象然后调用start方法,这样线程的run方法就会开始执行
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new ThreadTest();
        thread.start();
        thread.join();
    }
}
