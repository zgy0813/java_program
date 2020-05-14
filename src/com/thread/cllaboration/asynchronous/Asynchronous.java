package com.thread.cllaboration.asynchronous;


import java.util.Random;

/**
 * @author zgy
 */
public class Asynchronous {
    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor();
        // 子任务
        Callable<Integer> subTask = () -> {
            // 执行异步任务
            Random random = new Random();
            int millis = random.nextInt(1000);
            Thread.sleep(millis);
            return millis;
        };
        // 异步调用，返回一个MyFuture对象
        MyFuture<Integer> future = executor.execute(subTask);
        // 执行其他操作
        try {
            // 获取异步调用的结果
            Integer result= future.get();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
