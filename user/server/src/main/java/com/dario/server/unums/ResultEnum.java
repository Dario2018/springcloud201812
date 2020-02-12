package com.dario.server.unums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    PARAM_ERROR(1, "参数错误"),
    CART_ERROR(2, "购物车为空"),
    LOGIN_FAIL(3,"登陆失败"),
    ROLE_ERROR(4,"角色错误"),
    ;
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
