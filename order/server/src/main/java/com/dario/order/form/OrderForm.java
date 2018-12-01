package com.dario.order.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderForm {
    /*买家名字*/
    @NotEmpty(message="名字必填")
    private String name;
    /*买家电话*/
    @NotEmpty(message = "手机必填")
    private String phone;
    /*买家地址*/
    @NotEmpty(message = "地址必填")
    private String address;
    /*买家微信id*/
    @NotEmpty(message = "微信号必填")
    private String openid;

    /*购物车*/
    @NotEmpty(message = "购物车不能为空")
    private String items;
}
