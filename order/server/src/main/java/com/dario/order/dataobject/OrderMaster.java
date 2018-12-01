package com.dario.order.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@Table(name = "order_master")
public class OrderMaster {

    /*订单号*/
    @Id
    private String orderId;

   /*买家名字*/
   private String buyerName;

   /*买家手机号*/
    private String buyerPhone;

    /*买家地址*/
    private String buyerAddress;

    /*买家微信号openID*/
    private String buyerOpenId;

    /*订单总金额*/
    private BigDecimal orderAmount;

    /*订单状态，默认为0，新下单*/
    private Integer orderStatus;

    /*支付状态 默认为0,未支付*/
    private Integer payStatus;

    /*创建时间*/
    private Date createTime;

    /*更新时间*/
    private Date updateTime;

}
