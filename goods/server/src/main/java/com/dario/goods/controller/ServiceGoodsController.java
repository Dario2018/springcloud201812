package com.dario.goods.controller;

import com.dario.goods.VO.GoodsInfoVo;
import com.dario.goods.common.DecreaseStockInput;
import com.dario.goods.common.GoodsInfoOutput;
import com.dario.goods.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Dario
 * @Date 2018-12-27
 * @Description 作为服务直接的通讯入口，通过order服务可以调用到
 */
@RestController
@RequestMapping("/servicegoods")
@Slf4j
public class ServiceGoodsController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/msg")
    public String getGoodsMsg() {
        log.info("【getGodsMsg】获取信息 getGoods={}");
        return "this is goods message 2";
    }

    /**
     * 供订单服务调用的接口
     */

    @PostMapping("/listForOrder")
    public List<GoodsInfoOutput> listForOrder(@RequestBody List<String> goodsIdList) throws Exception {
        return goodsService.listForOrder(goodsIdList);
    }

    /*
     * 扣库存接口
     * */
    @PostMapping("/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList) {
        goodsService.decreaseStock(decreaseStockInputList);
    }

}
