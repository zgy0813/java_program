package com.thread.cllaboration.assemble;

public class AssemblePointDemo {
    static class Tourist extends Thread {
        AssemblePoint ap;

        public Tourist(AssemblePoint ap) {
            this.ap = ap;
        }

        @Override
        public void run() {
            try {
                // 模拟先各自独立运行
                Thread.sleep((int)(Math.random() * 1000));
                // 集合
                ap.await();
                System.out.println("arrived");
                // 集合后执行其他操作
            } catch (InterruptedException e) {}
        }
    }

    public static void main(String[] args) {
        int num = 10;
        Tourist[] threads = new Tourist[num];
        AssemblePoint ap = new AssemblePoint(num);
        for (int i = 0; i < num; i++) {
            threads[i] = new Tourist(ap);
            threads[i].start();
        }
    }
}
/*
各个线程分头行动， 各自到达一个集合点，再结合点需要集齐所有线程，交换数据然后执行下一步。怎么表示这种协作呢？
协作的变量依旧是一个数，这个数表示未到集合点的线程个数，初始值是线程个数，每个线程到达集合点后该值减一，如果不为0，表示还有别的线程没结束，进行等待，如果变为0，表示自己是最后一个线程，调用notifyAll唤醒所有线程
 */