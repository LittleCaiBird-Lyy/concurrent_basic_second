package com.thd.Line;

import java.util.Arrays;

public class MyArrayList implements MyList{

    //定义数组保存数据元素
    private Object[] elements;

    //定义初始容量
    private static final int DEFAULT_CAPACITY = 16;

    //定义保存数据元素个数
    private int size;

    //构造方法的作用就是给这个成员变量赋值
    public MyArrayList(){
        elements = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity){
        elements = new Object[initialCapacity];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        //判断线性表是否为空
        return size == 0;
    }

    @Override
    public void insert(int i, Object e) {
        //i处插入e,先判断i
        if (i < 0 || i > size){
            throw new IndexOutOfBoundsException(i + "越界");
        }
        //如果数组已满，对数组扩容
        if (size >= elements.length){
            //数组扩容
            expandSpace();
        }
        //从i开始，把元素依次后移(从后往前计算的思想)
        for (int j = size; j > i ; j--) {
            elements[j] = elements[j-1];
        }
        //把元素e存储到i位置
        elements[i] = e;
        //元素的个数+1
        size++;
    }

    //数组扩容
    private void expandSpace() {
        //定义一个更大的数组，默认按两倍扩容
        Object[] newElements = new Object[elements.length * 2];
        // 把原来数组的内容复制到新的数组当中
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        //让原来的数组名指向新的数组
        elements = newElements;
    }

    //判断线性表中是否包含元素e
    @Override
    public boolean contains(Object e) {
        return indexOf(e) >= 0 ;
    }

    //返回线性表中元素的第一次出现的索引值,不存在返回-1
    @Override
    public int indexOf(Object e) {
        //遍历数组
        if (e == null){
            //i < size已经写入的值,如果线性表中用户添加了Null
            for (int i = 0; i < size; i++) {
                if (elements[i] == null){
                    return i;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                if (e.equals(elements[i])){
                    return i;
                }
            }
        }
        return -1;
    }

    //在线性表中第一个与e相同的元素，并返回该元素
    @Override
    public Object remove(Object e) {
        //获得e在线性表中的索引值
        int i = indexOf(e);
        if (i < 0){
        return null;
        }
        return remove(i);
    }

    //删除线性表中索引值为i的元素，并返回该元素
    @Override
    public Object remove(int i) {
        //判断i是否越界-->插入个数=下标+1
        if (i < 0 || i >= size){
            throw new IndexOutOfBoundsException(i +"越界");
        }
        //把要删除的元素保存到局部变量当中
        Object old = elements[i];
        //把i+1开始的元素依次前移
        for (int j = i; j < size -1; j++) {
            elements[j] = elements[j+1];
        }
        //把最后的元素置为Null
        elements[size-1] = null;
        //修改元素个数
        size--;
        return old;
    }

    //使用元素e替换线性表中i位置的元素，并返回旧的元素
    @Override
    public Object replace(int i, Object e) {
        //判断索引值是否越界
        if (i < 0 || i >= size){
            throw new IndexOutOfBoundsException(i +"越界");
        }
        //把要替换的元素保存到局部变量当中
        Object old = elements[i];
        //替换
        elements[i] = e;
        //把原来的返回
        return old;
    }

    //返回索引值为I的元素
    @Override
    public Object get(int i) {
        //判断索引值是否越界
        if (i < 0 || i >= size){
            throw new IndexOutOfBoundsException(i +"越界");
        }
        return elements[i];
    }

    //在线性表元素P的前面插入元素e
    @Override
    public boolean inserBefore(Object p, Object e) {
        //确定元素p在线性表中的位置
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
        //确定元素p在线性表中的位置
        int i = indexOf(p);
        if (i < 0){
            return false;
        }
        insert(i+1,e);
        return true;
    }

    //重写toString

    @Override
    public String toString() {
       //把线性表中的每一个元素连接起来，遍历数组中的每一个元素
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size -1){
                sb.append(",");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
