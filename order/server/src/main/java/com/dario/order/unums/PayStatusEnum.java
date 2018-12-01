package com.dario.order.unums;

import lombok.Getter;

@Getter
public enum PayStatusEnum {
    WAIT(0,"未支付"),
    SUCCESS(1,"支付完成")
    ;
    private Integer code;
    private String message;
    PayStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }
}
