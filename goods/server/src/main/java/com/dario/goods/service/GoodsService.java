package com.dario.goods.service;

import com.dario.goods.common.DecreaseStockInput;
import com.dario.goods.common.GoodsInfoOutput;
import com.dario.goods.entity.Goods;


import java.util.List;

/*
*  ctrl + alt +B
*  查看接口所有实现类方法
* */
public interface GoodsService {

    //获取所有上架的商品
    public List<Goods> queryAllByGoodsStatus() throws Exception;

    //提交订单服务的接口
    public List<GoodsInfoOutput> listForOrder(List<String> goodsIdList) throws Exception;

    //扣除库存
    public void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
