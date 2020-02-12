package com.dario.goods.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Author:Dario
 * Date:2019-03-13
 * */
public class FileToolsUtil {


    private static final Long SERIALIZEVERSION=1L;

    /*
    * 解析xml类型文件
    * */
    public static Map<String,String> parseXml(HttpServletRequest request) throws Exception{
        Map<String,String> hashMap=new HashMap<String,String>();
        InputStream inputStream = request.getInputStream();//获取流
        SAXReader saxReader = new SAXReader();
        Document document=saxReader.read(inputStream);
        Element rootElement = document.getRootElement();//获取xml根节点
        List<Element> elements = rootElement.elements();//获取根节点的所有子节点
        for (Element e:elements)
            hashMap.put(e.getName(),e.getText());
        inputStream.close();
        return hashMap;
    }
}
