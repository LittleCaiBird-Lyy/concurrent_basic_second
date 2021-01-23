package com.thd.link;

/**
 * 通过接口定义一组线性表中的操作
 */
public interface MyList {

    //返回线性表中元素的个数
    int getSize();

    //判断线性表是否为空
    boolean isEmpty();

    //在线性表i索引值添加元素e
    void insert(int i , Object e);

    //判断线性表中是否包含元素e
    boolean contains(Object e);

    //返回线性表中元素的索引值
    int indexOf(Object e );

    //在线性表中第一个与e相同的元素，并返回该元素
    Object remove(Object e );

    //删除线性表中索引值为i的元素，并返回该元素
    Object remove(int i );

    //使用元素e替换线性表中i位置的元素，并返回旧的元素
    Object replace(int i ,Object e );

    //返回索引值为I的元素
    Object get(int i );

    //在线性表元素P的前面插入元素e
    boolean inserBefore(Object p ,Object e );

    //在线性表元素P的后面插入元素e
    boolean inserAfter(Object p ,Object e );

}
