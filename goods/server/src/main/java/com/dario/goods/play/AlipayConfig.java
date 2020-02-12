package com.dario.goods.play;

import com.dario.goods.entity.Goods;
import com.esotericsoftware.minlog.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * @author Dario
 * 描述： 实现支付功能
 * */
public class AlipayConfig {

    private static String partner="合作身份者ID-需要向alipay申请：https://b.alipay.com";

    private static String seller_id="收款账号";

    private static String SecurityCode="安全码";


    public static String makeOrderAlipayUrl(HttpServletRequest request, HttpServletResponse response, Goods goods) throws Exception {

        Map hm=new HashMap<String,String>();
        hm.put("_input_charset",request.getCharacterEncoding());
        hm.put("body","您在www.xxx.com上的订单");
        hm.put("discount","-5");
        hm.put("logistics_free","10");
        hm.put("logistics_payment","BUYER_PAY");
        hm.put("logistics_type","EXPRESS");
        hm.put("notify_url","http://www.xxx.com/notifyurl.jsp");
        hm.put("out_trade_no",goods.getGoodsId());
        hm.put("partner",partner);
        hm.put("agent",partner);
        hm.put("payment_type","1");
        hm.put("price","105.30");
        hm.put("quantity","1");
        hm.put("return_url","http://www.xx.com/ReturnUrl.jsp");
        hm.put("seller_email","alipay@cdd.com");
        hm.put("service","create_direct_pay_by_user");
        hm.put("subject","www.xx.com的订单");
        String payGateWay="https://www.alipay.com/cooperate/gateway.do?";

        return makeURL(hm,SecurityCode,request.getCharacterEncoding(),payGateWay);
    }

    public static String makeURL(Map hm,String securityCode,String charset,String payGateway) throws  Exception{

        List keys=new ArrayList(hm.keySet());
        Collections.sort(keys);
        StringBuffer content=new StringBuffer();
        for (int i=0;i<keys.size();i++){
            content.append(String.valueOf(keys.get(i)));
            content.append("=");
            content.append(String.valueOf(hm.get(String.valueOf(keys.get(i)))));
            if (i!=keys.size()-1)
                content.append("&");
        }

        content.append(securityCode);
        String sign=md5(content.toString(),charset);
        content.delete(0,content.length());
        content.append(payGateway);
        for (int i=0;i<keys.size();i++){
            content.append(keys.get(i));
            content.append("=");
            content.append(URLEncoder.encode(String.valueOf(hm.get(keys.get(i))),charset));
            content.append("&");
        }
        content.append("&sign_type=MD5");
        keys.clear();
        keys=null;
        return content.toString();
    }

    /**
     * 生成md5编码字符串
     * @param str 源字符串
     * @param charset 编码方式
     * */
    public static String md5(String str,String charset){
        if (null==str)
            return null;
        char[] hexDigits={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        MessageDigest md5MessageDigest=null;
        byte[] md5Bytes=null;
        char[] md5Chars=null;
        byte[] strBytes=null;
        try {
            strBytes=str.getBytes(charset);
            md5MessageDigest=MessageDigest.getInstance("MD5");
            md5MessageDigest.update(strBytes);
            md5Bytes=md5MessageDigest.digest();
            int j=md5Bytes.length;
            md5Chars=new char[j*2];
            int k=0;
            for (int i=0;i<j;i++){
                byte md5Byte=md5Bytes[i];
                md5Chars[k++]=hexDigits[md5Byte>>>4 & 0xf];
                md5Chars[k++]=hexDigits[md5Byte & 0xf];
            }
            return new String(md5Chars);

        }catch (NoSuchAlgorithmException e) {
            Log.error(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            Log.error(e.getMessage());
        }finally {
            md5MessageDigest=null;
            strBytes=null;
            md5Bytes=null;
            return null;
        }
    }

}
