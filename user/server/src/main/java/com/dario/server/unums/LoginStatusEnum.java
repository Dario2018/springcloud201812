package com.dario.server.unums;

import lombok.Getter;

@Getter
public enum LoginStatusEnum {
    LOGIN_USERORPASSWORD_NULL(-1, "用户名或密码不能为空"),
    LOGIN_SUCCESS(0, "登录成功"),
    LOGIN_NOT_REGISTRY(1, "当前用户还没有注册"),
    LOGIN_USER_BLOCKED(2, "当前用户已被锁定"),
    LOGIN_USER_ERROR(3, "用户名或密码错误"),
    LOGIN_EXCEPTION(403, "系统出现异常"),
    GET_VARIFY_CODE(4, "获取手机验证码异常"),
    ACTIVATE_VARIFY_CODE(5,"激活手机验证码失败");

    private Integer code;
    private String messages;

    LoginStatusEnum(Integer code, String messages) {
        this.code = code;
        this.messages = messages;
    }
}
