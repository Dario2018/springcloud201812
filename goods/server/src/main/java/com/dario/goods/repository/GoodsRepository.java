package com.dario.goods.repository;


import com.dario.goods.entity.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsRepository extends JpaRepository<Goods, String> {

    /*通过商品状态：上架*/
    public List<Goods> queryAllByGoodsStatus(Integer goodsStatus) throws Exception;

    public List<Goods> findGoodsByGoodsIdIn(List<String> goodsIdList);
}

