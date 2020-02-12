package com.dario.order.service;


import com.dario.order.dataobject.OrderDetail;
import com.dario.order.dto.OrderDTO;

import java.util.List;
/*
 *  ctrl + alt +B
 *  查看接口所有实现类方法
 * */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param CarDTO return
     */
    OrderDTO create(OrderDTO orderDTO) throws Exception;

    /**
     * 完结订单，只能卖家操作
     */

    OrderDTO finish(String orderId) throws Exception;

    /**
     * 通过orderid查询订单详情
     */

    List<OrderDetail> findOrderDetailListByOrderId(String orderId) throws Exception;

}
