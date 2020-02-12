package com.dario.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Test
    public void contextLoads() {
        //HashMap key经过hashCode 取值再hash取值
        Map<String,String> hasMap=new HashMap<>();
        hasMap.put("B","b");
        hasMap.put("C","c");
        hasMap.put("A","a");
        for (Map.Entry<String,String> entry:hasMap.entrySet()){
            System.out.println(entry.getKey()+"-->"+entry.getValue());
        }
        //LinkedHashMap 可以实现队列和堆栈
        Map<String,String> linkedHashMap=new LinkedHashMap<>();
        linkedHashMap.put("B","b");
        linkedHashMap.put("C","c");
        linkedHashMap.put("A","a");
        for (Map.Entry<String,String> entry:linkedHashMap.entrySet()){
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }
        String[] s = "ssss_aaa_bbh".split("_", 2);
        System.out.println(s[0]+"-"+s[1]);
    }

}

