package com.thread.cllaboration.asynchronous;


public class Asynchronous {
    public static void main(String[] args) {
        MyExecutor executor = new MyExecutor();
        // 子任务
        Callable<Integer> subTask = new Callable() {
            @Override
            public Integer call() throws Exception {
                // 执行异步任务
                int millis = (int) (Math.random() * 1000);
                Thread.sleep(millis);
                return millis;
            }
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
