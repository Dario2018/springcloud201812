package com.dario.goods.SimpleTon;

import java.util.ArrayList;
import java.util.List;

public class TestExtends {

    public static void main(String[] args) {
//        FatherTest father=new FatherTest("父亲的名字");
//        father.speak();
//        System.out.println("==========================================");
//        SonTest son=new SonTest("儿子的名字");
//        son.speak();
//
//        System.out.println("**************************************");
//        SonTest sonTest=new SonTest();
        List<String>  temp=new ArrayList<>();
        temp.add("hello");
        temp.add("world");
        temp.add(1,"my");
        for (String str:temp) {
            System.out.println(str);
        }

    }
}
