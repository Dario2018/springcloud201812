package com.dario.order.controller;

import com.dario.order.VO.ResultVO;
import com.dario.order.convert.OrderForm2OrderDTOConvert;
import com.dario.order.dto.OrderDTO;
import com.dario.order.exception.OrderException;
import com.dario.order.form.OrderForm;
import com.dario.order.service.OrderService;
import com.dario.order.unums.ResultEnum;
import com.dario.order.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
           log.error("【创建订单】参数不正确 orderForm={}",orderForm);
           throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        //orderForm ->orderDTO
        OrderDTO orderDTO=OrderForm2OrderDTOConvert.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】购物车信息为空");
            throw new OrderException(ResultEnum.CART_ERROR);
        }
        OrderDTO result=orderService.create(orderDTO);

        Map<String,String> map=new HashMap<>();
        Optional.ofNullable(map.put("orderId",result.getOrderId())).orElse(null);
         return ResultVOUtils.success(map);
    }
}
