package com.dario.server.unums;

import lombok.Getter;

@Getter
public enum RoleEnum {

    BUYER(1,"买家"),
    SELLER(2,"卖家"),
    ROLE_ERROR(0,"角色错误"),
    ;
    private Integer code;

    private String message;

    RoleEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
