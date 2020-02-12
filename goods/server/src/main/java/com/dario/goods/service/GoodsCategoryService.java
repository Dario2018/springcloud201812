package com.dario.goods.service;

import com.dario.goods.entity.GoodsCategory;

import java.util.List;
/*
 *  ctrl + alt +B
 *  查看接口所有实现类方法
 * */
public interface GoodsCategoryService {
    //获取所有类目
    List<GoodsCategory> findByCategoryTypeIn(List<Integer> categoryList) throws Exception;
}
