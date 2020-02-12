package com.dario.server.constanst;

public interface ConstantUtil {

    //    设置cookie常量
    String TOKEN = "token";
    //    过期时间
    Integer EXPIRE = 7200;
    //    微信登录id
    String OPENID = "openid";

    //    设置redis常量
    String TOKEN_TEMPLATE = "token_%s";
}
