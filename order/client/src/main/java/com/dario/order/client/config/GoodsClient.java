package com.dario.order.client.config;



import com.dario.order.common.GoodsInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "goods")
public interface GoodsClient {

    @GetMapping("/msg")
    public String goodsMsg();

    /**
     * 查询商品服务
     *这里要和ServiceGoodsClient相同p
    */
    @PostMapping("/goods/listForOrder")
    public List<GoodsInfoOutput> listForOrder(@RequestBody List<String> goodsIdList);
}
