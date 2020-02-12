package com.dario.server.utils;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Slf4j
public class HttpClientUtil {

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param url   发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        String urlNameString = url + "?" + param;
        try {
            URL realUrl = new URL(urlNameString);
            //打开和URL之间的连接
            URLConnection urlConnection = realUrl.openConnection();
            urlConnection.setRequestProperty("connection", "Keep-Alive");
            urlConnection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            urlConnection.connect();
            Map<String, List<String>> map = urlConnection.getHeaderFields();
            //遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("发送HttpClient:" + url, "结果:" + result, "请求方式: GET");
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {

            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        log.info("发送HttpClient:" + url, "结果:" + result, "请求方式: POST");
        return result;
    }

//    public static void main(String[] args) {
//
//        //String str = HttpClientUtil.sendPost("http://14.17.104.56:7000/httplogin", "{\"do\":\"Pmd.UserRequestPlatTokenByPasswordLoginUserPmd_C\",\"data\":{\"platid\":128,\"gameid\":9005,\"account\":\"helojo\",\"password\":\"e10adc3949ba59abbe56e057f20f883e\"},\"uid\":\"1000104\",\"gameid\":9005,\"zoneid\":108,\"unigame_plat_login\":\"6a8350ed90888d363f2b62f4434a7281-1493287668\",\"unigame_plat_timestamp\":1493287711}");
//
//
//        JsonObject jo_do = new JsonObject();
//        JsonObject jo_data = new JsonObject();
//        jo_data.addProperty("email", "01001111100100101010110101010101");
//        jo_data.addProperty("password", DigestUtils.md5Hex("100004"));
//
//        jo_do.addProperty("do", "Pmd.EmailRegistRequestCreateAccountLoginUserPmd_C");
//        jo_do.addProperty("data", jo_data.toString());
//
//        String unlibRes = HttpClientUtil.sendPost("", jo_do.toString());
//
//        log.info("返回结果：" + unlibRes);
//    }
}
