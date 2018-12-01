package com.dario.goods.common;

import lombok.Data;

@Data
public class DecreaseStockInput {

    private String goodsId;

    private Integer goodsQuantity;

    public DecreaseStockInput() {
    }

    public DecreaseStockInput(String goodsId, Integer goodsQuantity) {
        this.goodsId = goodsId;
        this.goodsQuantity = goodsQuantity;
    }
}
