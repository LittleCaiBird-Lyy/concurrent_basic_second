package com.thd.queue;

/**
 * 自定义队列为空的异常，该异常是运行时异常，不需要预处理
 * RuntimeException的子类就是运行时异常
 */
public class QueueEmptyException extends RuntimeException{
    public QueueEmptyException() {
        super();
    }

    //String参数，传递异常信息
    public QueueEmptyException(String message) {
        super(message);
    }
}
