package com.dario.goods.repository;

import com.dario.goods.entity.GoodsCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoodsCategoryRepository extends JpaRepository<GoodsCategory,Integer> {


  public List<GoodsCategory> queryGoodsCategoriesByCategoryType(List<Integer> categoryList) throws  Exception;

  public List<GoodsCategory> findByCategoryTypeIn(List<Integer> categoryList) throws Exception;

}