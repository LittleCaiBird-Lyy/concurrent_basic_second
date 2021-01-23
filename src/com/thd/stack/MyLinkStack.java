package com.thd.stack;


public class MyLinkStack implements MyStack{
    //存储栈顶的引用
    private Node top;
    //保存堆栈中元素的个数
    private int size;

    //定义一个内部类 ，描述列表中的节点
    private class Node{
        //数据
        Object data;
        //下一个节点的引用
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    //放回元素的个数
    @Override
    public int getSize() {
        return size;
    }

    //判断栈是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //压栈入栈
    @Override
    public void push(Object e) {
        //根据元素生成节点，插入链表头部
        Node pNode = new Node(e,top);
        //修改top
        top = pNode;
        size++;
    }

    //弹栈出栈
    @Override
    public Object pop() {
        //先判断堆栈是否为空
        if (size < 1){
            throw new StackOverflowError("栈已空");
        }
        //保存原来栈顶元素
        Object data = top.data;
        top = top.next;
        size--;
        return data;
    }

    //返回栈顶元素
    @Override
    public Object peek() {
        //先判断堆栈是否为空
        if (size < 1){
            throw new StackOverflowError("栈已空");
        }
        return top.data;
    }

    @Override
    public String toString() {
        //把链表中各个结点的数据返回
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(Node pNode = top; pNode != null ; pNode = pNode.next){
            sb.append(pNode.data);
            if (pNode.next != null){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
