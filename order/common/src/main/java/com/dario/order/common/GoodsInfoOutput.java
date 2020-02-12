package com.dario.order.common;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsInfoOutput {

    //商品的名称
    private String goodsId;
    //商品名字
    private String goodsName;
    //商品价格
    private BigDecimal goodsPrice;
    //商品库存
    private int goodsStock;
    //商品描述
    private String goodsDecription;
    //商品图案
    private String goodsIcon;
    //商品上下架 0：上架，1：下架
    private Integer goodsStatus;
    //所属类目编号
    private Integer categoryType;
}
