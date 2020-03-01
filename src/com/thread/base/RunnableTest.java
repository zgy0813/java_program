package com.thread.base;

/**
 * 实现Runnable的run方法
 */
public class RunnableTest implements Runnable {
    @Override
    public void run() {
        // 每个Thread都有一个id和name，可以判断在哪个线程中执行的
        System.out.println("thread id: " + Thread.currentThread().getId());
        System.out.println("thread name: " + Thread.currentThread().getName());
    }

    /**
     * 还是要创建Thread对象，传递一个Runnable对象，再执行start方法
     */
    public static void main(String[] args) {
        Thread helloThread = new Thread(new RunnableTest());
        helloThread.start();
    }
}
