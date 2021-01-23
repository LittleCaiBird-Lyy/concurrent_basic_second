package com.thd.Dual;

import org.w3c.dom.Node;

public class MyDualLinkedList implements MyList{

    //定义一个内部类描述双向链表的节点
    private class Node{
        Object data;
        //指向前驱
        Node prev;
        //指向后继
        Node next;

        public Node(Object data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    //指向头节点
    private Node first;
    //指向尾节点
    private Node last;
    //保存元素个数
    private int size;


    ////返回线性表中元素的个数
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
        //检查索引是否越界
        if (i < 0 || i > size){
            throw  new IndexOutOfBoundsException(i + " 越界");
        }
        //如果i== 0 在头部添加元素
        if (i == 0){
            addFirst(e);
        }else if (i == size){
            //如果 i== size 在尾部添加元素
            addLast(e);
        }else {
            //找到i节点，在i节点的前面插入元素
            Node pNode = getNode(i);
            Node preNode = pNode.prev;
            //生成新的节点
            Node newNode = new Node(e,preNode,pNode);
            //修改前驱节点的后续
            preNode.next = newNode;
            pNode.prev = newNode;
            size++;
        }

    }

    //返回索引值对应的节点
    private Node getNode(int i) {
        //
        Node pNode = first;
        for (int j = 0; j < i; j++) {
            pNode = pNode.next;
        }
        return pNode;
    }

    //在尾部部添加元素e
    public void addLast(Object e) {
        Node pNode = last;
        //生成一个新的节点
        Node newNode = new Node(e,last,null);
        if (pNode == null){
            first = newNode;
        }else {
            pNode.next = newNode;
        }
        last = newNode;
        size++;
    }

    //在头部添加元素e
    public void addFirst(Object e) {
        Node pNode = first;
        //生成新的节点
        Node newNode = new Node(e,null,first);
        //修改first指向新的节点
        first = newNode;
        if(pNode == null){
            //刚刚插入的节点即是头节点又是尾节点
            last = newNode;
        }else {
            pNode.prev = newNode;
        }
        //元素个数加1
        size++;
    }

    //判断线性表中是否包含元素e
    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0;
    }

    //返回线性表中元素第一次出现的位置的索引值
    @Override
    public int indexOf(Object e) {
        //保存e在链表中的索引值
        int i = 0;
        //依次遍历链表中的各个节点，比较节点的元素与指定的e是否一样
        if (e.equals(null)){
            for (Node pNode = first; pNode != null ; pNode = pNode.next){
                if (pNode.data == null){
                    return i;
                }
                i++;
            }
        }else {
            for (Node pNode = first; pNode != null ; pNode = pNode.next){
                if (e.equals(pNode.data)){
                    return i;
                }
                i++;
            }

        }
        return -1;
    }

    //在线性表中第一个与e相同的元素，并返回该元素
    @Override
    public Object remove(Object e) {
        int i = indexOf(e);
        if (i < 0){
            return null;
        }
        return remove(i);
    }

    //删除线性表中索引值为i的元素，并返回该元素
    @Override
    public Object remove(int i) {
        //检查索引是否越界
        if (i < 0 || i > size){
            throw  new IndexOutOfBoundsException(i + " 越界");
        }
        //先找到i对应的节点
        Node pNode = getNode(i);
        //删除节点的前驱
        Node preNode = pNode.prev;
        //删除节点的后继
        Node nextNode = pNode.next;
        if (preNode == null){
            //删除的头节点
            first = nextNode;
        }else {
            preNode.next = nextNode;
        }
        if (nextNode == null){
            //删除的尾节点
            last = preNode;
        }else {
            nextNode.prev = preNode;
        }
        size--;
        return pNode.data;
    }

    //使用元素e替换线性表中i位置的元素，并返回旧的元素
    @Override
    public Object replace(int i, Object e) {
        //检查索引是否越界
        if (i < 0 || i > size){
            throw  new IndexOutOfBoundsException(i + " 越界");
        }
        //找到索引值i的节点
        Node node = getNode(i);
        Object old = node.data;
        node.data = e;
        return old;
    }

    //返回索引值为I的元素
    @Override
    public Object get(int i) {
        //检查索引是否越界
        if (i < 0 || i > size){
            throw  new IndexOutOfBoundsException(i + " 越界");
        }
        Node node = getNode(i);
        return node.data;
    }

    //在线性表元素P的前面插入元素e
    @Override
    public boolean inserBefore(Object p, Object e) {
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
        int i = indexOf(p);
        if (i < 0){
            return false;
        }
        insert(i+1,e);
        return true;
    }

    //删除头元素
    public Object removeFirst(){
        return remove(0);
    }

    //删除最后一个元素
    public Object removeLast(){
        return remove(size-1);
    }

    //返回头元素
    public Object getFirst(){
        return get(0);
    }
    //返回尾元素
    public Object getLast(){
        return get(size-1);
    }


    @Override
    public String toString() {
        //依次遍历各个节点，把元素转化为字符串
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Node node = first ; node != null ; node = node.next){
            sb.append(node.data);
            //逗号分隔
            if (node != last){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
