package com.enjoyedu.second;

//LinkedList实现LRU算法
public class LruLinkedList<T> extends LinkedList<T>{
    int memory_size;//限定内存大小，也就是缓存大小
    static final int DEFAULT_CAP = 5;

    public LruLinkedList(){
        this.memory_size = DEFAULT_CAP;
    }

    public LruLinkedList(int memory_size){
        this.memory_size = memory_size;
    }

    //添加节点
    public void lruPut(T data){
        if (size >= memory_size){
            removeLast();
            put(data);
        }else {
            put(data);
        }
    }

    //LRU删除
    public T lruDelete(){
        return removeLast();
    }

    //LRU查找
    public T lruGet(int index){
        checkPositionIndex(index);
        Node node = list;
        Node pre = list;
        for (int i = 0; i <index; i++) {
            pre = node;
            node = node.next;
        }
        T resultData =node.data;
        //将访问的节点移到表头
        pre.next = node.next;
        Node head = list;
        node.next = head;
        list = node;
        return  resultData;
    }

    public static void main(String[] args) {
        LruLinkedList<Integer> lruLinkedList = new LruLinkedList<>(5);
        for(int i = 0; i <4; i++) {
            lruLinkedList.lruPut(i);
        }
        lruLinkedList.toString();
        System.out.println(lruLinkedList.lruGet(3));
        lruLinkedList.toString();
        lruLinkedList.lruPut(20);
        lruLinkedList.toString();

        lruLinkedList.lruPut(18);
        lruLinkedList.toString();

    }
}
