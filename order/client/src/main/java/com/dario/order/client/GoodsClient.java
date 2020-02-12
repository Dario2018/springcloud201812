package com.dario.order.client;


import com.dario.order.common.DecreaseStockInput;
import com.dario.order.common.GoodsInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import java.util.List;

@FeignClient(name = "goods", fallback = GoodsClient.GoodsClientFallback.class)
public interface GoodsClient {

    @GetMapping("/servicegoods/msg")
    public String getMessage();

    @PostMapping("/servicegoods/listForOrder")
    public List<GoodsInfoOutput> listForOrder(@RequestBody List<String> goodsIdList);

    /*扣库存*/
    @PostMapping("/servicegoods/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);


    //hystrix+Feign实现服务降级
    @Component
    public static class GoodsClientFallback implements GoodsClient {

        public GoodsClientFallback() {
        }

        @Override
        public String getMessage() {
            return null;
        }

        @Override
        public List<GoodsInfoOutput> listForOrder(List<String> goodsIdList) {
            return null;
        }

        @Override
        public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList) {
            System.out.println("扣除库存发生错误");
        }
    }

}
