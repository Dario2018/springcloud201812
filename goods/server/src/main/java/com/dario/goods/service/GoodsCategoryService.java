package com.dario.goods.service;

import com.dario.goods.entity.GoodsCategory;

import java.util.List;

public interface GoodsCategoryService {
    //获取所有类目
    List<GoodsCategory> findByCategoryTypeIn(List<Integer> categoryList) throws Exception;
}
