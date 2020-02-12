package com.dario.goods.service;

import com.dario.goods.GoodsApplicationTests;
import com.dario.goods.entity.Goods;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class GoodsServiceTest extends GoodsApplicationTests {

    @Autowired
    private GoodsService goodsService;

    @Test
    public void queryAllByGoodsStatus() throws Exception {
        List<Goods> list = goodsService.queryAllByGoodsStatus();
        Assert.assertTrue(list.size() > 0);
    }
}