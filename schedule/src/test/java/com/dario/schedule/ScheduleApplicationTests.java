package com.dario.schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScheduleApplicationTests {

    @Test
    public void contextLoads() {
        //注意equals是对象才能使用
        String str=null;
//        if (str.equals("")||str==null)//报java.lang.nullpointException
        if ("".equals(str)||null==str)//不报错
        System.out.println("hello");
    }

}

