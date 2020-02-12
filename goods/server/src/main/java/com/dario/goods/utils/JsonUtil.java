package com.dario.goods.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
    private static ObjectMapper objectMapper=new ObjectMapper();

    /**
     * @author Administrator
     * @date 2018.12.11
     * @description：转换为json字符串
     * */
    public static String toJson(Object object){

        try {
            return  objectMapper.writeValueAsString(object);
        }catch (JsonProcessingException e){
         e.printStackTrace();
        }
        return null;
    }
}
