package com.thd.link;

import com.thd.Line.MyList;
import org.w3c.dom.Node;

public class MySingleLink implements MyList {

    //定义一个内部类表示单向列表的节点
    private class Node{
        //保存数据
        Object data;
        //保存下个节点的引用
        Node next;

        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    //定义头节点
    private Node head;
    //保存元素个数
    private int size;

    //返回线性表中元素的个数
    @Override
    public int getSize() {
        return size;
    }

    //判断线性表是否为空
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //在线性表i索引值添加元素e
    @Override
    public void insert(int i, Object e) {
        //判断索引值是否越界
        if (i < 0 || i > size){
            throw new IndexOutOfBoundsException(i +"越界");
        }
        //创建节点
        Node newNode = new Node(e,null);
        //头节点为null的情况,链表不存在，刚刚添加的节点就是头节点
        if (head == null){
            head = newNode;
        }else {
            //在0位置插入节点
            if (i == 0){
                newNode.next = head;
                head = newNode;
            }else {
                //其余位置插入节点,先找到i-1这个节点
                Node pNode = head;
                for (int j = 1; j < i; j++) {
                    pNode = pNode.next;
                }
                //注意.先修改新节点的next指针域，再修改i-1节点的指针域
                newNode.next = pNode.next;
                pNode.next = newNode;
            }
        }
        //元素个数
        size++;
    }

    //判断线性表中是否包含元素e
    @Override
    public boolean contains(Object e) {
        return indexOf(e) >=0;
    }

    //返回元素第一次出现的索引值
    @Override
    public int indexOf(Object e) {
        //由于链表没有索引值，所以要弄一个数保存索引值
        int i = 0;
        Node pNode = head;
        while(pNode != null){
            if (e == null && pNode.data == null){
                return i;
            }else if (e != null && e.equals(pNode.data)) {
              return i;
          }
            i++;
            pNode = pNode.next;
        }

        return -1;
    }


    //在线性表中第一个与e相同的元素，并返回该元素
    @Override
    public Object remove(Object e) {
        //找到e的index
        int i = indexOf(e);
        if (i < 0){
            return null;
        }
        return remove(i);
    }

    //删除线性表中索引值为i的元素，并返回该元素
    @Override
    public Object remove(int i) {
        //判断索引值是否越界
        if (i < 0 || i >= size){
            throw new IndexOutOfBoundsException(i +"越界");
        }
        Node pNode = head;
        //删除头节点
        if (i == 0){
            head = head.next;
            size--;
            //返回删除头节点的数据
            return pNode.data;
        }
        //找到i-1节点
        for (int j = 1; j < i; j++) {
            pNode = pNode.next;
        }
        //保存删除节点的data
        Object old = pNode.next.data;
        //修改i-1节点的next指针域，指向i+1节点
        pNode.next = pNode.next.next;
        size--;
        return old;
    }

    //使用元素e替换线性表中i位置的元素，并返回旧的元素
    @Override
    public Object replace(int i, Object e) {
        checkIndex(i);
        //找到i节点
        Node pNode = getNode(i);
        //保存原来的数据
        Object old = pNode.data;
        //替换
        pNode.data = e ;
        return old;
    }

    //返回索引值为I的元素
    @Override
    public Object get(int i) {
        checkIndex(i);
        Node node = getNode(i);
        return node.data;
    }

    //定义一个方法返回i索引值的元素
    private Node getNode(int i){
        //size个数最后一个data是null
        if (i < 0 || i >= size){
            return null;
        }
        if (i == 0){
            return head;
        }
        //找到i节点,pNode是i节点前一个节点
        Node pNode = head;
        for (int j = 1; j <i ; j++) {
            pNode = pNode.next;
        }
        return pNode;

    }

    //检查索引值是否越界
    private void checkIndex(int i){
        //判断索引值是否越界
        if (i < 0 || i >= size){
            throw new IndexOutOfBoundsException(i +"越界");
        }
    }

    //在线性表元素P的前面插入元素e
    @Override
    public boolean inserBefore(Object p, Object e) {
        //找p的位置
        int i = indexOf(p);
        if (i < 0){
            return false;
        }
        insert(i,e);
        return true;
    }

    //在线性表元素P的后面插入元素e
    @Override
    public boolean inserAfter(Object p, Object e) {
        //找p的位置
        int i = indexOf(p);
        if (i < 0){
            return false;
        }
        insert(i+1,e);
        return true;
    }

    @Override
    public String toString() {
        //把链表中各个节点的数据域连接起来
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node pNode = head;
        while (pNode != null){
            sb.append(pNode.data);
            //使用逗号分隔
            if (pNode.next != null){
                sb.append(",");
            }
            //指针下移
            pNode = pNode.next;
        }
        sb.append("]");
        return sb.toString();
    }
}
