package com.thread.cllaboration.asynchronous;

// 在java中表示子任务的接口是Callable
public interface Callable<V> {
    V call() throws Exception;
}
