package com.dario.server.utils;






import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密
 * @author Dario
 * @date 2019-01029
 * */
public class MD5 {
       private static final char[] HAX_DIGEST="0123456789ABCDEF".toCharArray();
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
        if (StringUtils.isEmpty(str)){
            return null;
        }
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] bytes=md5.digest(str.getBytes("utf-8"));
        StringBuilder stringBuilder=new StringBuilder(bytes.length*2);
        for (int i=0;i<bytes.length;i++){
            stringBuilder.append(HAX_DIGEST[bytes[i] >> 4 & 0x0f]);
            stringBuilder.append(HAX_DIGEST[bytes[i] & 0x0f]);
        }
        return stringBuilder.toString();
    }

}
