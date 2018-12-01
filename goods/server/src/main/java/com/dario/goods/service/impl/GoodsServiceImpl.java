package com.dario.goods.service.impl;

import com.dario.goods.common.GoodsInfoOutput;
import com.dario.goods.entity.Goods;
import com.dario.goods.enums.GoodsStatusEnum;
import com.dario.goods.repository.GoodsRepository;
import com.dario.goods.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsRepository goodsRepository;
    @Override
    public List<Goods> queryAllByGoodsStatus() throws Exception {
        return goodsRepository.queryAllByGoodsStatus(GoodsStatusEnum.UP.getCode());
    }

    @Override
    public List<GoodsInfoOutput> listForOrder(List<String> goodsIdList) throws Exception {
        List<GoodsInfoOutput> goodsInfoOutputList=null;
        Optional.ofNullable(goodsInfoOutputList=goodsRepository.findGoodsByGoodsIdIn(goodsIdList)).orElse(null);
        return goodsInfoOutputList;
    }
}
