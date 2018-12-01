package com.dario.order.convert;

import com.dario.order.dataobject.OrderDetail;
import com.dario.order.dto.OrderDTO;
import com.dario.order.exception.OrderException;
import com.dario.order.form.OrderForm;
import com.dario.order.unums.ResultEnum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConvert {

    public static OrderDTO convert(OrderForm orderForm){
        Gson gson=new Gson();
        OrderDTO orderDTO=new OrderDTO();
        orderDTO.setBuyerOpenId(orderForm.getOpenid());
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> orderDetails=new ArrayList<>();
        try {
              orderDetails=gson.fromJson(orderForm.getItems(),
                new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
          log.error("【json转化】错误 string={}",orderForm.getItems());
          throw new OrderException(ResultEnum.PARAM_ERROR);
        }
       orderDTO.setOrderDetailList(orderDetails);
        return orderDTO;
    }
}
