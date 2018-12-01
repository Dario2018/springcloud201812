package com.dario.goods.enums;

import lombok.Getter;

/*
* 商品状态信息
* */
@Getter
public enum GoodsStatusEnum {
    UP(0,"上架"),
    DOWN(1,"下架"),
    ;

    private Integer code;

    private String message;

    GoodsStatusEnum(Integer code,String message){
        this.code=code;
        this.message=message;
    }

}
