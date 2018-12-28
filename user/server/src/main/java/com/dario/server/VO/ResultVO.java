package com.dario.order.VO;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private String message;
    private T t;
}
