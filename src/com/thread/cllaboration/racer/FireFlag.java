package com.thread.cllaboration.racer;

public class FireFlag {
    private volatile boolean fired = false;

    public synchronized void waitForFire() throws InterruptedException {
        while (!fired) {
            wait();
        }
    }

    public synchronized void fire() {
        this.fired = true;
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 10;
        FireFlag fireFlag = new FireFlag();
        Thread[] racers = new Thread[num];
        for (int i = 0; i < num; i++) {
            racers[i] = new Racer(fireFlag);
            racers[i].start();
        }
        Thread.sleep(1000);
        fireFlag.fire();
    }
}
/*
同时开始类似于运动员比赛，在听到比较开始枪响后同时开始
- 有一个主线程和N个子线程，每个子线程模拟一个运动员，主线程模拟裁判，他们协作的共享变量时一个开始信号
这里启用了10个子线程，每个子线程都在等待fire信号，主线程调用fire()后各个子线程才开始执行后续操作
- 子线程应该调用waitForFire等待枪响，而主线程应该调用fire发射比赛开始信号
 */