package com.dario.goods.repository;

import com.dario.goods.GoodsApplicationTests;
import com.dario.goods.entity.GoodsCategory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@Component
public class GoodsCategoryRepositoryTest extends GoodsApplicationTests {

    @Autowired
    private GoodsCategoryRepository goodsCategoryRepository;

    @Test
    public void queryGoodsCategoriesByCategoryType() throws Exception {
        List<GoodsCategory> list = goodsCategoryRepository.queryGoodsCategoriesByCategoryType(Arrays.asList(1, 2));
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<GoodsCategory> lists = goodsCategoryRepository.findByCategoryTypeIn(Arrays.asList(1, 2));
        Assert.assertTrue(lists.size() > 0);
    }


}