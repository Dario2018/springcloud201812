package com.dario.order.utils;

import com.dario.order.VO.ResultVO;

public class ResultVOUtils {

    public static ResultVO success(Object object){
        ResultVO resultVO =new ResultVO();
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setT(object);
        return resultVO;
    }
}
