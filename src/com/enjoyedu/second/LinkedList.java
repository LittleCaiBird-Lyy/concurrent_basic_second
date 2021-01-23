package com.enjoyedu.second;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现LRU而建立的单链表
 *      LeastRecentlyUsed:新数据插到链表头部
 *                        被访问到的数据移到链表头部
 *                        链表满了的时候尾部的数据被丢弃
 */
public class LinkedList<T> {

    Node list;
    int size;//节点个数

    public LinkedList(){
        size = 0;
    }

    class Node{
        T data;
        Node next;

        public Node(T data,Node next){
            this.data = data;
            this.next = next;
        }
    }

    //在头部添加节点
    public void put(T data){
        //定义头部节点
        Node head = list;
        //新建data的节点
        Node curNode = new Node(data,list);
        //curNode成为新的头节点
        list = curNode;
        //节点个数+1
        size++;
    }

    //在链表index位置插入一个元素
    public void put(int index,T data){
        //index长度是否合法
        checkPositionIndex(index);
        //插入节点上一个节点
        Node head = list;
        //插入的节点
        Node curNode = list;
        //找到Index在的节点Node里面也没有int index属性？并且index之前的节点发生变化
        for (int i = 0; i < index; i++) {
            //curNode
            head = curNode;
            //以前的head与curNode.next连接
            curNode = curNode.next;
        }
        Node newNode = new Node(data,curNode);
        size++;
    }

    //删除头节点
    public T remove(){
        if (list !=null){
            //node被删除节点
            Node node = list;
            //node的下一个节点变成头节点
            list = list.next;
            list.next = null;//GC回收
            size--;
            return node.data;
        }
        return null;
    }

    //删除指定节点
    public T remove(int index){
        checkPositionIndex(index);
        //删除节点上一个节点
        Node head = list;
        //删除节点
        Node node = list;
        for (int i = 0; i < index; i++) {
            //直接head = node.next?
            head = node;
            node = node.next;
        }
        head.next = node.next;
        node.next = null;//GC回收
        size--;
        return node.data;
    }

    //删除尾部节点
    public T removeLast(){
        if (list != null){
        Node cur = list;
        Node node = list;
        while (cur.next != null){
            node = cur;
            cur = cur.next;
        }
        node.next = null;
        size--;
        return node.data;
        }
        return null;
    }

    //修改节点
    public void set(int index , T newData){
        checkPositionIndex(index);
        Node head = list;
        for (int i = 0; i < index; i++) {
            head = head.next;
        }
        head.data = newData;
    }

    //查询节点--get头部节点
    public T get(){
        Node node = list;
        if (node != null){
            return node.data;
        }
        return null;
    }

    //查索引处节点
    public T get(int index){
        checkPositionIndex(index);
        Node cur = list;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.data;
    }

    @Override
    public String toString() {
        Node node = list;
        for (int i = 0; i < size; i++) {
            System.out.print(node.data + " ");
//			System.out.print(" ");
            node = node.next;
        }
        System.out.println();
        return super.toString();
    }




    public void checkPositionIndex(int index){
        if (!(index >= 0 && index <= size)){
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
    }


}


