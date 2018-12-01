package com.dario.order.service;


import com.dario.order.dto.OrderDTO;

public interface OrderService {

    /**
     * 创建订单
    * @param CarDTO
     * return
    * */
    OrderDTO create(OrderDTO orderDTO);
}
