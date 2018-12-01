package com.dario.order.dto;

import lombok.Data;

@Data
public class CarDTO {

    /*商品的id*/
   private String productId;
    /*商品的数量*/
    private Integer productQuantity;

    /*反序列化需要用上*/
    public CarDTO(){}

    public CarDTO(String productId,Integer productQuantity){
        this.productId=productId;
        this.productQuantity=productQuantity;
    }
}
