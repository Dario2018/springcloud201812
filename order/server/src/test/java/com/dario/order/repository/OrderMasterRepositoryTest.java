package com.dario.order.repository;

import com.dario.order.OrderApplicationTests;
import com.dario.order.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave() {
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("12345678");
        orderMaster.setBuyerAddress("广东湛江");
        orderMaster.setBuyerName("dario");
        orderMaster.setBuyerOpenId("chen2018");
        orderMaster.setBuyerPhone("13432824841");
        orderMaster.setCreateTime(new Date());
        orderMaster.setOrderAmount(new BigDecimal(10.01));
        orderMaster.setOrderStatus(0);
        orderMaster.setUpdateTime(new Date());
        orderMaster.setPayStatus(0);
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}