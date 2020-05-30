package com.thread.base;

/**
 * 竞争条件
 *
 * @author zgy
 */
public class CounterThread extends Thread {
    private static int counter = 0;

    @Override
    public void run() {
        int max = 1000;
        for (int i = 0; i < max; i++) {
            counter++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new CounterThread();
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }
        System.out.println(counter);
    }
}
/*
当多个线程访问和操作同一个对象时，最终执行结果和执行时序有关，可能正确也可能不正确
有一个共享的静态变量counter，在main方法中创建1000个线程，每个线程对counter循环加1000次，等线程结束后，期望值是100w，但是实际每次结果都不一样，一般都不是100w，原因是==counter++这个操作不是原子操作==，它分为三个步骤：
- 取counter的当前值
- 在当前值的基础上+1
- 将新值重新赋值给counter

其中两个线程可能同时执行了第一步，取到了相同的值，比如都取到了100，第一个线程执行完一次后counter=101，而第二个counter执行完第一次后还是101，最终的结果就和预期不一样了
 */