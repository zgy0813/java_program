package com.thread.cllaboration.asynchronous;
// 为表示异步调用的结果，我们定义了一个接口MyFuture，这个接口get方法返回真正的结果，如果结果还没有计算完成，get会阻塞直到计算完成
public interface MyFuture<V> {
    V get() throws Exception;
}
