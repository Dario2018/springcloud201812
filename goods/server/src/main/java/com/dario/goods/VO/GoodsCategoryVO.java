package com.dario.goods.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GoodsCategoryVO {

    @JsonProperty("name")
    private  String categoryName;

    @JsonProperty("type")
    private  Integer categoryType;

    @JsonProperty("goods")
    private List<GoodsInfoVo> goodsInfoVoList;
}
