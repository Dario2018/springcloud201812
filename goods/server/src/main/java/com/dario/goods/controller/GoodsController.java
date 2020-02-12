package com.dario.goods.controller;

import com.dario.goods.VO.GoodsCategoryVO;
import com.dario.goods.VO.GoodsInfoVo;
import com.dario.goods.VO.ResultVO;
import com.dario.goods.entity.Goods;
import com.dario.goods.entity.GoodsCategory;
import com.dario.goods.service.GoodsCategoryService;
import com.dario.goods.service.GoodsService;
import com.dario.goods.utils.ResultsVoUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/goods")
public class GoodsController {

//    private static final Logger LOGGER=Logger.getLogger(GoodsController.class.getName());

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private GoodsCategoryService goodsCategoryService;

    /*
     * 1.查询所有在架的商品
     * 2.获取类目type列表
     * 3.查询类目
     * 4.构造数据返回
     * */
    @GetMapping("/goodslist")
    @CrossOrigin  //支持跨域访问
    public ResultVO goodsList() {
        System.out.println("调用了goodList....");
        try {
            //  1.查询所有在架的商品
            List<Goods> goodsList = goodsService.queryAllByGoodsStatus();
            //  2.获取类目type列表
            List<Integer> categoryTypes = goodsList.stream()
                    .map(Goods::getCategoryType).collect(Collectors.toList());
            System.out.println("categoryTypes:" + categoryTypes.size());
            //  3.查询类目
            List<GoodsCategory> goodsCategoryList = goodsCategoryService.findByCategoryTypeIn(categoryTypes);
            System.out.println("goodsCategoryList:" + goodsCategoryList.size());
            //  4.构造数据返回
            List<GoodsCategoryVO> goodsCategoryVOList = new ArrayList<GoodsCategoryVO>();

            for (GoodsCategory goodsCategory : goodsCategoryList) {
                GoodsCategoryVO goodsCategoryVO = new GoodsCategoryVO();
                goodsCategoryVO.setCategoryName(goodsCategory.getCategoryName());
                goodsCategoryVO.setCategoryType(goodsCategory.getCategoryType());

                List<GoodsInfoVo> goodsInfoVos = new ArrayList<GoodsInfoVo>();

//                for (Goods goods : goodsList) {
//                    if (goods.getCategoryType().equals(goodsCategory.getCategoryType())) {
//                        GoodsInfoVo goodsInfoVo = new GoodsInfoVo();
//                        BeanUtils.copyProperties(goods, goodsInfoVo);
//                        goodsInfoVos.add(goodsInfoVo);
//                    }
//                }
                goodsList.stream().forEach(goods -> {
                    if (goods.getCategoryType().equals(goodsCategory.getCategoryType())){
                        GoodsInfoVo goodsInfoVo=new GoodsInfoVo();
                        BeanUtils.copyProperties(goods,goodsInfoVo);
                        goodsInfoVos.add(goodsInfoVo);
                    }
                });

                goodsCategoryVO.setGoodsInfoVoList(goodsInfoVos);
                goodsCategoryVOList.add(goodsCategoryVO);
            }
            return ResultsVoUtils.success(goodsCategoryVOList);
        } catch (Exception e) {
//            LOGGER.log(Level.ALL,e.getMessage());
            return ResultsVoUtils.error(null);
        }

    }
}
