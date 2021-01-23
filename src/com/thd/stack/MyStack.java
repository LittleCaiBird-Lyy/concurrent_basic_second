package com.thd.stack;

public interface MyStack {

    //放回元素的个数
    int getSize();

    //判断栈是否为空
    boolean isEmpty();

    //压栈入栈
    void push(Object e);

    //弹栈出栈
    Object pop();

    //返回栈顶元素
    Object peek();


}
