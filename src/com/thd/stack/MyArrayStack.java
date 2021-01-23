package com.thd.stack;

import java.util.Arrays;

public class MyArrayStack implements MyStack{

    //定义数组来保存堆栈的元素
    private Object[] elements;

    //堆栈的默认容量
    private static final int DEFAULT_CAPACITY = 16;

    //栈顶指针
    private int top;

    //无参构造默认初始化
    public MyArrayStack() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    //指定容量初始化
    public MyArrayStack(int initialCapacity) {
        elements = new Object[initialCapacity];
    }



    //放回元素的个数
    @Override
    public int getSize() {
        return top;
    }

    //判断栈是否为空
    @Override
    public boolean isEmpty() {
        return top <= 0;
    }

    //压栈入栈
    @Override
    public void push(Object e) {
        //判断堆栈是否已满，如果堆栈已满，数组需要扩容
        if (top >= elements.length){
            //定义一个更大的数组
            Object[] newData = new Object[elements.length *2];
            //把原来的数组内容复制到大的数组中
            for (int i = 0; i < elements.length; i++) {
                newData[i] = elements[i];
            }
            //让原来的数组名指向新的数组
            elements = newData;
        }
        //把元素存储到栈顶指针指向的位置
        elements[top] = e;
        top++;
    }

    //弹栈出栈
    @Override
    public Object pop() {
        //判断堆栈是否为空
        if (top <= 0){
            throw new StackOverflowError("栈已空");
        }
        //栈顶指针下移
        top--;
        return elements[top];
    }

    //返回栈顶元素,不删除
    @Override
    public Object peek() {
        //判断堆栈是否为空
        if (top <= 0){
            throw new StackOverflowError("栈已空");
        }
        //栈顶指针下移
        return elements[top-1];
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("[");
        //从栈顶到栈底的顺序添加各个元素
        for (int i = top -1; i >= 0; i--) {
            sb.append(elements[i]);
            //元素之间可以用逗号来分割
            if (i > 0){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
