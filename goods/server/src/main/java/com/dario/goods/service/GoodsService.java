package com.dario.goods.service;

import com.dario.goods.common.GoodsInfoOutput;
import com.dario.goods.entity.Goods;


import java.util.List;

public interface GoodsService {

    //获取所有上架的商品
    public List<Goods> queryAllByGoodsStatus() throws Exception;

    //提交订单服务的接口
    public List<GoodsInfoOutput> listForOrder(List<String> goodsIdList) throws Exception;
}
