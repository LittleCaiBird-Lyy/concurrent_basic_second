package com.thd.Line;

/**
 * 测试
 */
public class MyArrayListTest {
    public static void main(String[] args) {
        //1.创建一个MyArrayList对象
        MyArrayList list1 = new MyArrayList();

        //2.判断是否为空
        System.out.println(list1.isEmpty());//true
        System.out.println(list1.getSize());//0

        //3.tianjia
        list1.insert(0,"aa");
        list1.insert(1,"bb");
        list1.insert(2,"cc");

        //4.打印
        System.out.println(list1);

        //5
        System.out.println(list1.indexOf("cc"));
        System.out.println(list1.indexOf("bb"));
        System.out.println(list1.indexOf("dd"));

        //6删除
        list1.remove("dd");
        System.out.println(list1);
        list1.remove("bb");
        System.out.println(list1);
        list1.remove(0);
        System.out.println(list1);

        //7
        list1.insert(0,"xx");
        list1.insert(0,"oo");
        list1.insert(0,"yy");
        //[yy,oo,xx,cc]
        System.out.println(list1);

        list1.replace(0,"YY");
        System.out.println(list1);

        System.out.println(list1.get(0));
        System.out.println(list1.get(1));
        System.out.println(list1.get(2));
        //System.out.println(list1.get(33));

        list1.inserBefore("oo","jj");
        list1.inserAfter("oo","JJ");
        System.out.println(list1);




    }
}
