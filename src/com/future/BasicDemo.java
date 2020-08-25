package com.future;


import java.util.concurrent.*;

public class BasicDemo {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        Callable<String> callable1 = () -> {
            Thread.sleep(5000);
            String out = "11111111111111111111111111";
            System.out.println(out);
            return out;
        };
        FutureTask futureTask1 = new FutureTask<>(callable1);
        new Thread(futureTask1).start();

        Callable<String> callable2 = () -> {
            Thread.sleep(1000);
            String out = "22222222222222222222222222";
            System.out.println(out);
            return out;
        };

        Runnable runnable1 = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String out = "333333333333333333333333333";
            System.out.println(out);
        };
        new Thread(runnable1).start();

        FutureTask futureTask2 = new FutureTask<>(callable2);
        new Thread(futureTask2).start();

        String out1 = (String) futureTask1.get();
        System.out.println("out1="+out1);

        String out2 = (String) futureTask2.get();
        System.out.println("out2="+out2);
    }
}
