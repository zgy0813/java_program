package com.thread.sync;

public class WaitThread extends Thread {
    private volatile boolean fire = false;

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!fire) {
                    wait();
                }
            }
            System.out.println("fired");
        } catch (InterruptedException e) {

        }
    }

    public synchronized void fire() {
        this.fire = true;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        System.out.println("fire");
        waitThread.fire();
    }
}

/*
- 一个主线程，一个WaitThread线程，协作的变量条件是fire，WaitThread等待该变量变成true，在不为true的时候调用wait，主线程设置该变量并调用notify
- 两个线程都要访问协作的变量fire，容易出现竞技状态，所以相关代码都需要被synchronized保护，实际上==wait/notify方法只能在synchronized代码块内被调用==
- ==虽然在synchronized方法内，但调用wait()时，线程会释放对象锁==
    - 把当前线程放入条件等待队列，释放对象锁，阻塞等待，线程状态变成WAITING或TIMED_WAITING
    - 等待时间到或被其他线程调用notify或者notifyAll从条件队列中移除，这时，要重新竞争对象锁
        - 如果能够获得锁，线程状态变成RUNNABLE，并从wait调用中返回
        - 否则，该线程加入对象锁等待队列，线程状态变成BLOCKED
    - 线程从wait调用中返回后，不代表其等待条件就一定能成立，它需要重新检查其等待条件
- 调用notify会把在条件队列中等待的线程唤醒并从队列中移除，但它不会释放对象锁，只有在包含notify的synchronized代码块执行完后，等待的线程才会从wait调用中返回
- 我们在设计多线程协作时，需要清除协作的共享变量和条件是什么

 */