package com.dario.goods.enums;

import lombok.Getter;

/*
 * 商品状态信息
 * */
@Getter
public enum GoodsStatusEnum {
    UP(0, "上架"),
    DOWN(1, "下架"),
    GOODS_NOT_EXIST(2,"该商品不存在"),
    GOODS_AMOUT_NOT_ENOUGH(3,"库存不足")
    ;

    private Integer code;

    private String message;

    GoodsStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
