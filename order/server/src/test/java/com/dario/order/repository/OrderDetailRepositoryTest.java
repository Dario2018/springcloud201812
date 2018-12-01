package com.dario.order.repository;

import com.dario.order.OrderApplicationTests;
import com.dario.order.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@Component
public class OrderDetailRepositoryTest extends OrderApplicationTests{

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void testSave(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("12345678");
        orderDetail.setOrderId("12345678");
        orderDetail.setProductId("12345678");
        orderDetail.setProductName("香菇鸡");
        orderDetail.setProductPrice(new BigDecimal(0.1));
        orderDetail.setProductQuantity(9);
        orderDetail.setProductIcon("/upload/images/food.jpg");
        OrderDetail result=orderDetailRepository.save(orderDetail);
        Assert.assertTrue(result!=null);
    }
}