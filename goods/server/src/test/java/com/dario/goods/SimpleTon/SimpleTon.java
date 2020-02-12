package com.dario.goods.SimpleTon;

public class SimpleTon {

    private static  SimpleTon simpleTon;

    private SimpleTon(){}

    public static SimpleTon getInstance(){
        if (simpleTon==null){
            synchronized(simpleTon){
                if (simpleTon==null)
                simpleTon=new SimpleTon();
            }
        }
        return simpleTon;
    }

}
