package com.thread.cllaboration.producer;

import java.util.ArrayDeque;
import java.util.Queue;

public class MyBlockingQueue<E> {
    private Queue<E> queue = null;
    private int limit;

    public MyBlockingQueue(int limit) {
        this.limit = limit;
        queue = new ArrayDeque<>(limit);
    }

    public synchronized void put(E e) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(e);
        notifyAll();
    }

    public synchronized E take() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        E e = queue.poll();
        notifyAll();
        return e;
    }

    // 主程序
    public static void main(String[] args) {
        MyBlockingQueue<String> queue = new MyBlockingQueue<>(10);
        new Producer(queue).start();
        new Consumer(queue).start();
    }
}

/*
- MyBlockingQueue是一个长度有限的队列，长度通过构造方法的参数进行传递
- put是给生产者使用的，往队列上放数据，满了就wait，放完之后就notifyAll。通知可能的消费者
- take是给消费者使用的，从队列中取数据，如果为空就wait，取完之后调用notifyAll，通知可能的生产者
- put和take都调用了wait，但是他们等待的条件是不同的，put等待的是队列不为满，而take等待的是队列不为空，但是他们都会加入相同的条件等待队列，由于条件不同但是又使用相同的等待队列，所以要调用notifyAll而不是notify，因为notify唤醒的是同类线程就起不到协调的作用了
- 只能有一个条件等待队列，这是wait/notify机制的局限性

 */