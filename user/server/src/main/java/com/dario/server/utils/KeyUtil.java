package com.dario.server.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一在的主键
     * 格式：时间加随机数
     */
    public static synchronized String getuniqueKey() {
        Random random = new Random();
        Integer inte = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(inte);
    }
}
