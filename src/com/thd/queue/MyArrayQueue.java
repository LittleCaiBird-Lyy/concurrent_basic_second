package com.thd.queue;

/**
 * 对列的顺序，存储的实现
 */
public class MyArrayQueue {
    //定义数组存储队列中的元素
    private Object[] elements;

    //数组默认容量
    private static final int DEFAULT_CAPACITY = 8;

    //队首
    private int front;
    //队尾
    private int rear;
    //元素个数
    private int size;

    //构造方法
    public MyArrayQueue() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayQueue(int initialCapacity) {
        elements = new Object[initialCapacity];
    }

    //返回元素个数
    public int getSize(){
        return  size;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //入队
    public void enQueue(Object e){
        //如果队列已满，可以对数组扩容
        if (size >= elements.length){
            expandQueue();
        }
        //把元素存储到rear指针指向的单元
        elements[rear] = e;
        //rear指针后移，因为是循环的形式不能简单的++
        rear = (rear+1) % elements.length;
        size++;

    }

    //扩容
    private void expandQueue() {
        //定义一个更大的数组
        Object[] newElements = new Object[DEFAULT_CAPACITY * 2];
        //将原来数组内容复制到新的数组当中，从队首开始复制到新数组索引值0开始的位置
        for (int i = 0; i < size; i++) {
            //由于是环形的结构，所以复制的时候要调整位置
            newElements[i] = elements[front];
            front = (front +1) % elements.length;
        }
        //让原来的数组名指向新的数组
        elements = newElements;
        //调整新的堆栈与队尾指针
        front = 0;
        rear = size;
    }

    //出队
    public Object deQueue(){
        //如果队列为空
        if (size <= 0){
            //抛出一个队列为空的异常
            throw  new QueueEmptyException("队列为空");
        }
        //队列不为空，把front指向的元素返回
        Object old = elements[front];
        front = (front+1) % elements.length;
        size--;
        return old;

    }

    //返回队首的元素
    public Object peek(){
        //如果队列为空
        if (size <= 0){
            //抛出一个队列为空的异常
            throw  new QueueEmptyException("队列为空");
        }
        return elements[front];
    }
}
