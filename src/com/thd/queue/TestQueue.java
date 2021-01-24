package com.thd.queue;

public class TestQueue {
    public static void main(String[] args) {
        MyArrayQueue queue = new MyArrayQueue();

        queue.enQueue("a");
        queue.enQueue("b");
        queue.enQueue("c");
        queue.enQueue("d");

        System.out.println(queue.peek());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        //System.out.println(queue.deQueue());

        queue.enQueue("1");
        queue.enQueue("2");
        queue.enQueue("3");
        queue.enQueue("4");
        queue.enQueue("5");
        queue.enQueue("6");
        queue.enQueue("7");
        queue.enQueue("8");
        queue.enQueue("9");
        queue.enQueue("10");
        System.out.println(queue.deQueue());
    }
}
