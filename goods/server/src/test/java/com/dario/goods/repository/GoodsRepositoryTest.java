package com.dario.goods.repository;

import com.dario.goods.GoodsApplicationTests;
import com.dario.goods.entity.Goods;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.junit.Assert.*;

@Component
public class GoodsRepositoryTest extends GoodsApplicationTests {

    @Autowired
    private GoodsRepository goodsRepository;
    @Test
    public void queryAllByGoodsStatus() throws  Exception{
        List<Goods> list=goodsRepository.queryAllByGoodsStatus(0);
        Assert.assertTrue(list.size()>0);
    }
}