package com.thread.sync;

public class CounterThread extends Thread {
    Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.incr();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        Counter counter = new Counter();
        Thread[] threads = new Thread[num];
        for (int i = 0; i < num; i++) {
            threads[i] = new CounterThread(counter);
            threads[i].start();
        }
        for (int i = 0; i < num; i++) {
            threads[i].join();
        }
        System.out.println(counter.getCount());
    }
}
/*
- ==synchronized实例方法实际保护的同一个对象的方法调用==，确保同时只能有一个线程执行
- 多个线程可以同时执行同一个synchronized实例方法，只要他们访问的对象是不同的即可

```
// 这里t1和t2两个线程是可以同时执行Counter的incr方法的，因为他们访问的不是同一个对象
Counter counter1 = new Counter();
Counter counter2 = new Counter();
Thread t1 = new new CounterThread(counter1);
Thread t2 = new new CounterThread(counter2);
t1.start();
t2.start();
```
- synchronized实例方法保护的是当前实例对象this，==this对象有一个锁和一个等待队列，锁只能被一个线程持有，其他视图获得同样锁的线程需要等待==。执行过程大致如下：
    - 尝试获得锁，如果获得锁，继续下一步，否则加入等待队列，阻塞并等待唤醒
    - 执行实例方法体代码
    - 释放锁，如果等待队列上有等待的线程，从中取一个并唤醒，如果有多个等待的线程，唤醒哪一个是不确定的，不保证公平性
- 当前线程不能获得锁的时候，它会加入等待队列等待，线程的状态变为BLOCKED
- ==synchronized保护的是对象而不是代码，对于同一个对象的两个方法incr和getCount，一个线程执行incr，另一个线程执行getCount是不能同时执行的==，会被synchronized同步顺序执行
- synchronized方法不能防止非synchronized方法被同时执行。所以在保护变量时，需要在所有访问该变量的方法上加上synchronized。


 */