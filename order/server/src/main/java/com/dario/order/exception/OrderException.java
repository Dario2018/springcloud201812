package com.dario.order.exception;

import com.dario.order.unums.ResultEnum;

public class OrderException extends RuntimeException {

    private Integer code;

    public OrderException(Integer code, String messages) {
        super(messages);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code=resultEnum.getCode();
    }
}
