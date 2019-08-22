package com.dario.apigateway.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class FileToolsUtil {


    private static final Long SERIALIZEVERSION=1L;

    /*
    * 解析xml类型文件
    * */
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{
        Map<String,String> hashMap=new HashMap<String,String>();
        InputStream inputStream = request.getInputStream();
        return null;
    }
}
