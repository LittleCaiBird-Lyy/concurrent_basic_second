package com.thd.queue;

/**
 * 队列的链式存储
 */
public class MyLinkQueue {

    private class Node{
        Object element;
        Node next;

        public Node(Object element, Node next) {
            super();
            this.element = element;
            this.next = next;
        }
    }

    //队首
    private Node front ;
    //队尾
    private Node rear;
    //元素的个数
    private int size;

    //返回元素的个数
    public int getSize(){
        return size;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    //入队
    public void enQueue(Object e ){
        //根据添加的元素生成一个节点
        Node newNode = new Node(e,null);
        //节点连接到队列当作
        if (rear == null){
            //这添加的第一个元素既是头节点也是尾节点
            rear = newNode;
            front = newNode;
        }else {
            //把节点连接到队列的尾部
            rear.next = newNode;
            //rear指针指向新添加的元素
            rear = newNode;
        }
        size++;
    }

    //出队
    public Object deQueue(){
        //判断队列是否为空
        if (size<=0){
            throw new QueueEmptyException("对列为空");
        }
        Object old = front.element;
        //指针下移
        front = front.next;
        //如果出队后队列为空，调整尾指针
        if (front == null){
            rear = null;
        }
        size--;
        return old;
    }

    //返回队首元素
    public Object peek(){
        if (size <= 0){
            throw new QueueEmptyException("对列为空");
        }
        return front.element;
    }
}
