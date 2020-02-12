package com.dario.goods.service.impl;

import com.dario.goods.entity.GoodsCategory;
import com.dario.goods.repository.GoodsCategoryRepository;
import com.dario.goods.service.GoodsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsCategoryServcieImpl implements GoodsCategoryService {

    @Autowired
    private GoodsCategoryRepository goodsCategoryRepository;

    @Override
    public List<GoodsCategory> findByCategoryTypeIn(List<Integer> categoryList) throws Exception {
        return goodsCategoryRepository.findByCategoryTypeIn(categoryList);
    }
}
