package com.dario.goods.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GoodsInfoVo {

    @JsonProperty("id")
    private String goodsId;
    //商品名字
    @JsonProperty("name")
    private String goodsName;
    //商品价格
    @JsonProperty("price")
    private BigDecimal goodsPrice;

    //商品描述
    @JsonProperty("decription")
    private String goodsDecription;

    //商品图案
    @JsonProperty("icon")
    private String goodsIcon;

}
