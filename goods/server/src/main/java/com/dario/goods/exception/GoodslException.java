package com.dario.goods.exception;

import com.dario.goods.enums.GoodsStatusEnum;

public class GoodslException extends RuntimeException{

    private Integer code;
    public GoodslException(Integer code, String messages){
        super(messages);
        this.code=code;
    }
    public GoodslException(GoodsStatusEnum goodsStatusEnum){
        super(goodsStatusEnum.getMessage());
        this.code=goodsStatusEnum.getCode();
    }
}
