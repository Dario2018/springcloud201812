package com.dario.order.dataobject;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * @author Dario
 * @date 2018-12-27
 * @description 订单明细表
 * */
@Data
@Entity
@Table(name = "order_detail")
public class OrderDetail {

    @Id
    private String detailId;

    /**
     * 订单id.
     */
    private String orderId;

    /**
     * 商品id.
     */
    private String goodsId;

    /**
     * 商品名称.
     */
    private String goodsName;

    /**
     * 商品单价.
     */
    private BigDecimal goodsPrice;

    /**
     * 商品数量.
     */
    private Integer goodsQuantity;

    /**
     * 商品小图.
     */
    private String goodsIcon;
}
