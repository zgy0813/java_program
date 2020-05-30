package com.thread.cllaboration.producer;

// 一个简单的生产者
public class Producer extends Thread {
    MyBlockingQueue<String> queue;

    public Producer(MyBlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (true) {
                String task = String.valueOf(num);
                queue.put(task);
                System.out.println("producer task " + task);
                num++;
                Thread.sleep((int) (Math.random() * 100));
            }
        } catch (InterruptedException e) {

        }
    }
}
