package com.dario.goods.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "goods")
public class Goods {
    //商品的名称
    @Id
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

    //创建时间
    private Date createTime;
    //    更新时间
    private Date updateTime;


}
