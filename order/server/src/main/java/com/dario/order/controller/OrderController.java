package com.dario.order.controller;

import com.dario.order.VO.ResultVO;
import com.dario.order.convert.OrderForm2OrderDTOConvert;
import com.dario.order.dto.OrderDTO;
import com.dario.order.exception.OrderException;
import com.dario.order.form.OrderForm;
import com.dario.order.service.OrderService;
import com.dario.order.enums.ResultEnum;
import com.dario.order.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
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

    /**
     * 生成订单详情
     *
     * @auhor Dario
     * @Date 2018-12-27
     */
    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {

        try {
            if (bindingResult.hasErrors()) {
                log.error("【创建订单】参数不正确 orderForm={}", orderForm);
                throw new OrderException(ResultEnum.PARAM_ERROR.getCode(), bindingResult.getFieldError().getDefaultMessage());
            }
            //orderForm ->orderDTO
            OrderDTO orderDTO = OrderForm2OrderDTOConvert.convert(orderForm);

            if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
                log.error("【创建订单】购物车信息为空");
                throw new OrderException(ResultEnum.CART_ERROR);
            }
            OrderDTO result = orderService.create(orderDTO);

            Map<String, String> map = new HashMap<>();
            Optional.ofNullable(map.put("orderId", result.getOrderId())).orElse(null);
            return ResultVOUtils.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVOUtils.error(null);
        }
    }

    /**
     * 由卖家修改订单状态为完结
     */
    @GetMapping("/finish")
    public ResultVO<OrderDTO> finish(@RequestParam("orderId") String orderId) {
        try {
            OrderDTO orderDTO = orderService.finish(orderId);

            if (orderDTO == null)
                return ResultVOUtils.error(null);
            else
                return ResultVOUtils.success(orderDTO);

        } catch (Exception e) {
//            e.printStackTrace();
            return ResultVOUtils.error(e.getMessage());
        }
    }

    /**
     * 由买家实现支付功能并修改订单状态，
     * 支付完成时发送信息通知买家（待完成）
     */

    /*
     * 测试
     * */
    @RequestMapping("/test")
    public String test() {
        return "hello";
    }
}
