package com.dario.goods.service;

import com.dario.goods.GoodsApplicationTests;
import com.dario.goods.entity.GoodsCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class GoodsCategoryServiceTest extends GoodsApplicationTests {

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    @Test
    public void queryGoodsCategoriesByCategoryType() throws Exception {
        List<GoodsCategory> list = goodsCategoryService.findByCategoryTypeIn(Arrays.asList(1, 2));
        Assert.assertTrue(list.size() > 0);
    }
}