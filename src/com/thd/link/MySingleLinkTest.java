package com.thd.link;


public class MySingleLinkTest {
    public static void main(String[] args) {

        //1.创建列表
        MySingleLink link = new MySingleLink();

        System.out.println(link.isEmpty());
        System.out.println(link.getSize());

        link.insert(0,"aa");
        link.insert(0,"bb");
        link.insert(0,"cc");
        link.insert(0,"dd");

        System.out.println(link);

        System.out.println(link.indexOf("dd"));
        System.out.println(link.indexOf("aa"));
        System.out.println(link.indexOf("xx"));

        System.out.println(link.remove("xx"));//null
        System.out.println(link.remove("bb"));
        System.out.println(link);

        System.out.println(link.get(0));
        System.out.println(link.replace(0,"CC"));
        System.out.println(link);

        System.out.println(link.inserBefore("aa","bb"));
        System.out.println(link);
        System.out.println(link.inserAfter("bb","BB"));
        System.out.println(link);

    }
}
