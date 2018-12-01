package com.dario.goods.client;

import com.dario.goods.common.DecreaseStockInput;
import com.dario.goods.common.GoodsInfoOutput;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "goods")
public interface GoodsClient {

    @PostMapping("/goods/listForOrder")
    public List<GoodsInfoOutput> listForOrder(@RequestBody List<String> goodsIdList);

/*扣库存*/
    @PostMapping("/goods/decreaseStock")
    public void decreaseStock(@RequestBody List<DecreaseStockInput> decreaseStockInputList);
}
